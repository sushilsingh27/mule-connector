package com.manh.connectors.caas.internal.operations;

import com.manh.connectors.caas.api.ConfigurationServiceException;
import com.manh.connectors.caas.internal.StaticConfigCache;
import com.manh.connectors.caas.model.ApplicationConfiguration;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import java.util.Map;

public class ReadPropertiesOperation {

	@MediaType("*/*")
	@Alias(value = "Read-Properties")
	public Map<String, String> readDocument() throws ConfigurationServiceException {

		// retrieve the configuration in the static cache.
		java.util.Optional<ApplicationConfiguration> appConfig = StaticConfigCache.get().findOne();

		Map<String, String> prop = appConfig.get().getProperties();

		if (prop == null)
			throw new ConfigurationServiceException(
					"Could not find properties in application: " + appConfig.get().getName());

		try {
			return prop;

		} catch (Exception ex) {
			throw new ConfigurationServiceException(ex);
		}

	}

}
