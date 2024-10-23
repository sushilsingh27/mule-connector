package com.manh.connectors.caas.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

public class ApplicationConfiguration implements Serializable {

	private final String name;
	private final String version;
	private final String environment;
	private final Map<String, String> properties;
	private final ConfigurationDataWrapper dataWrapper;

	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

	public static ApplicationConfigurationBuilder builder() {
		return new ApplicationConfigurationBuilder();
	}

	ApplicationConfiguration(String name, String version, String environment, Map<String, String> properties,
			ConfigurationDataWrapper dataWrapper) {
		this.name = name;
		this.version = version;
		this.environment = environment;
		this.properties = properties;
		this.dataWrapper = dataWrapper;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getEnvironment() {
		return environment;
	}

	public Map<String, String> getProperties() {

		Map<String, String> properties = this.properties;

		if (dataWrapper != null) {
			return dataWrapper.wrapProperties(properties);
		}

		return properties;
	}

	public String readProperty(String key) {

		logger.debug("Call to read key {}", key);

		String prop = null;

		String wrappedKey = findWrappedKey(key);

		// try with this one
		prop = properties.get(wrappedKey);

		if (prop != null) {
			if (dataWrapper != null) {
				prop = dataWrapper.wrapValue(prop);
			}
			return prop;
		}

		return null;

	}

	public ConfigurationDataWrapper getDataWrapper() {
		return dataWrapper;
	}

	private String findWrappedKey(String plainKey) {

		if (dataWrapper == null) {
			return plainKey;
		}

		logger.debug("Invoking data wrapper to unwrap existing keys...");

		for (String wrappedKey : properties.keySet()) {
			if (dataWrapper.wrapKey(wrappedKey).equals(plainKey)) {
				return wrappedKey;
			}
		}

		logger.debug("key not found, resorting to unwrapped key...");

		return plainKey;
	}

}
