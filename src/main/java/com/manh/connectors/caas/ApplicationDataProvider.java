package com.manh.connectors.caas;

import com.manh.connectors.caas.model.ApplicationConfiguration;
import java.util.Map;

/**
 * Abstracts access to Rest API
 */
public interface ApplicationDataProvider {

	/**
	 * Convenience instance to improve readability of the code.
	 */
	static ApplicationDataProviderFactory factory = new ApplicationDataProviderFactory();

	/**
	 * Load the low level information of an application.
	 * 
	 * @param name
	 * @param version
	 * @param environment
	 * @return
	 * @throws ConfigurationNotFoundException
	 */
	Map<String, Object> loadApplication(String name, String version, String environment)
			throws ConfigurationNotFoundException;

	ApplicationConfiguration loadApplicationConfiguration(String name, String version, String environment)
			throws ConfigurationServiceException;
}
