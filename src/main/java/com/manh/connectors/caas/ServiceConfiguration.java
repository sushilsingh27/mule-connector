package com.manh.connectors.caas;

import java.util.Map;

/**
 * Provides an abstraction of application configuration
 */
public interface ServiceConfiguration {

    String getServiceUrl();

    String getApplication();

    String getVersion();

    String getEnvironment();

    Map<String, String> getCustomHeaders();
}
