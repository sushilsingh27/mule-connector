package com.manh.connectors.caas.client;

import com.manh.connectors.caas.ApplicationDataProvider;
import com.manh.connectors.caas.ConfigurationServiceException;
import com.manh.connectors.caas.ServiceConfiguration;
import com.manh.connectors.caas.model.ApplicationConfiguration;
import com.manh.connectors.caas.model.ApplicationConfigurationBuilder;
import com.manh.connectors.caas.model.ConfigurationDataWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.*;

public class DefaultApplicationDataProvider implements ApplicationDataProvider {

	private static final Logger logger = LoggerFactory.getLogger(DefaultApplicationDataProvider.class);

	private final String baseUrl;
	private final Client restClient;
	private final ServiceConfiguration config;

	public DefaultApplicationDataProvider(String baseUrl, Client restClient, ServiceConfiguration config) {
		this.baseUrl = baseUrl;
		this.restClient = restClient;
		this.config = config;
	}

	@Override
	public Map<String, Object> loadApplication(String name, String version, String environment) {

		WebTarget target = restClient.target(baseUrl).path(name).path(version).path(environment);

		// read it as a java map
		Map<String, Object> result = applyHeaders(target.request(), config).accept(MediaType.APPLICATION_JSON)
				.get(Map.class);

		// this potentially logs sensitive information
		// we need to remove the properties before logging.

		if (logger.isDebugEnabled()) {
			Map<String, Object> loggableResult = new LinkedHashMap<>(result);
			loggableResult.remove("properties");
			logger.debug("Got settings from configuration server: {}", loggableResult);
		}

		return result;
	}

	@Override
	public ApplicationConfiguration loadApplicationConfiguration(String name, String version, String environment)
			throws ConfigurationServiceException {

		return loadApplicationConfiguration(name, version, environment, null, 50);
	}

	private ApplicationConfiguration loadApplicationConfiguration(String name, String version, String environment,
			ConfigurationDataWrapper wrapper, int depth) throws ConfigurationServiceException {
		ApplicationConfigurationBuilder retBuilder = ApplicationConfiguration.builder().setName(name)
				.setEnvironment(environment).setVersion(version);

		if (depth == 0) {
			logger.warn(
					"Reached depth 0 while recursively load importApp configurations. This may indicate a cycle in the importApp/child relationship.");
			return retBuilder.build();
		}

		if (wrapper != null) {
			retBuilder.setDataWrapper(wrapper);
		}

		// load from the API
		Map<String, Object> appData = loadApplication(name, version, environment);

		// get the properties
		Map<String, String> properties = (Map) appData.get("properties");
		if (properties == null) {
			properties = Collections.emptyMap();
		}

		retBuilder.setProperties(properties);

		return retBuilder.build();
	}

	private Invocation.Builder applyHeaders(Invocation.Builder builder, ServiceConfiguration config) {

		logger.debug("Called apply headers, configured headers are: {}", config.getCustomHeaders());

		if (config.getCustomHeaders() == null) {
			return builder;
		}

		for (Map.Entry<String, String> header : config.getCustomHeaders().entrySet()) {
			builder.header(header.getKey(), header.getValue());
		}

		return builder;
	}

}
