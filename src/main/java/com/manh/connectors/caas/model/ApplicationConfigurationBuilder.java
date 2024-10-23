package com.manh.connectors.caas.model;

import java.util.Collections;
import java.util.Map;

public class ApplicationConfigurationBuilder {
	private String name;
	private String version;
	private String environment;
	private Map<String, String> properties;
	private ConfigurationDataWrapper dataWrapper;

	public ApplicationConfigurationBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ApplicationConfigurationBuilder setVersion(String version) {
		this.version = version;
		return this;
	}

	public ApplicationConfigurationBuilder setEnvironment(String environment) {
		this.environment = environment;
		return this;
	}

	public ApplicationConfigurationBuilder setProperties(Map<String, String> properties) {
		this.properties = properties;
		return this;
	}

	public ApplicationConfigurationBuilder setDataWrapper(ConfigurationDataWrapper wrapper) {
		this.dataWrapper = wrapper;
		return this;
	}

	public ApplicationConfiguration build() {

		if (properties == null) {
			properties = Collections.emptyMap();
		}

		return new ApplicationConfiguration(name, version, environment, Collections.unmodifiableMap(properties),
				dataWrapper);
	}

}