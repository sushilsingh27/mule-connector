package com.manh.connectors.caas;

public class ConfigurationNotFoundException extends ConfigurationServiceException {

    public ConfigurationNotFoundException(String message) {
        super(message);
    }

    public ConfigurationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
