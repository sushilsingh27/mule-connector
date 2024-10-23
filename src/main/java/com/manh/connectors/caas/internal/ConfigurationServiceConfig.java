package com.manh.connectors.caas.internal;

import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Password;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import com.manh.connectors.caas.internal.security.encryption.binary.jce.algorithms.EncryptionAlgorithm;
import com.manh.connectors.caas.internal.security.encryption.binary.jce.algorithms.EncryptionMode;

import com.manh.connectors.caas.ServiceConfiguration;

import java.util.Map;

@Configuration
public class ConfigurationServiceConfig implements ServiceConfiguration {

	@Parameter
	@Placement(order = 1)
	private String serviceUrl;
	@Parameter
	@Placement(order = 2)
	private String application;
	//@Parameter
	@Optional(defaultValue = "v1")
	@Placement(order = 3)
	private String version;
	@Parameter
	@Placement(order = 4)
	private String environment;
	@Parameter
	@Optional
	@Placement(order = 5)
	private Map<String, String> customHeaders;
	
	@Parameter
	@Optional(defaultValue = "AES")
	@Placement(tab = "SecureProperties", order = 1)
	private EncryptionAlgorithm encryptionAlgorithm;

	@Parameter
	@Optional(defaultValue = "CBC")
	@Placement(tab = "SecureProperties", order = 2)
	private EncryptionMode encryptionMode;

	@Parameter
	@Placement(tab = "SecureProperties", order = 3)
	@Password
	private String key;

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public Map<String, String> getCustomHeaders() {
		return customHeaders;
	}

	public void setCustomHeaders(Map<String, String> customHeaders) {
		this.customHeaders = customHeaders;
	}
	
	private SecurePropertyPlaceholderModule securePropertyPlaceholderModule;

	public SecurePropertyPlaceholderModule getSecurePropertyPlaceholderModule() {
		return securePropertyPlaceholderModule;
	}

	public void setSecurePropertyPlaceholderModule(SecurePropertyPlaceholderModule securePropertyPlaceholderModule) {
		this.securePropertyPlaceholderModule = securePropertyPlaceholderModule;
	}

	public EncryptionAlgorithm getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setEncryptionAlgorithm(EncryptionAlgorithm encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public EncryptionMode getEncryptionMode() {
        return encryptionMode;
    }

    public void setEncryptionMode(EncryptionMode encryptionMode) {
        this.encryptionMode = encryptionMode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
}
