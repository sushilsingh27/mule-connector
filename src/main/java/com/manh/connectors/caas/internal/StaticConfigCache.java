package com.manh.connectors.caas.internal;

import com.manh.connectors.caas.api.ConfigurationServiceException;
import com.manh.connectors.caas.model.ApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * This class is a temporary workaround until I find a more elegant way of
 * making the configurations available to the module and just loading them once.
 */
public class StaticConfigCache {
	
	static Logger LOGGER = LoggerFactory.getLogger(StaticConfigCache.class);

	private Map<String, List<ApplicationConfiguration>> staticConfigurationsCache;
	private Map<String, ConfigurationServiceConfig> serviceConfigs;

	private static StaticConfigCache instance = new StaticConfigCache();

	private StaticConfigCache() {
		staticConfigurationsCache = new HashMap<>();
		serviceConfigs = new HashMap<>();
	}

	public static StaticConfigCache get() {
		return instance;
	}

	public synchronized void store(String configId, ConfigurationServiceConfig serviceConfig,
			ApplicationConfiguration config) {
		List<ApplicationConfiguration> serviceCache = staticConfigurationsCache.getOrDefault(configId,
				new LinkedList<>());

		ApplicationConfiguration existing = serviceCache.stream().filter(a -> sameCoordinates(a, config)).findAny()
				.orElse(null);

		// if is brand new, add it.
		if (existing == null) {
			serviceCache.add(config);
		} else {
			// update the cache.
			serviceCache.remove(existing);
			serviceCache.add(config);

		}

		// update the cache.
		staticConfigurationsCache.put(configId, serviceCache);
		serviceConfigs.put(configId, serviceConfig);
	}

	private boolean sameCoordinates(ApplicationConfiguration a, ApplicationConfiguration b) {
		return a.getName().equals(b.getName()) && a.getVersion().equals(b.getVersion())
				&& a.getEnvironment().equals(b.getEnvironment());
	}

	public synchronized Optional<ApplicationConfiguration> find(String configName) {
		List<ApplicationConfiguration> serviceCache = staticConfigurationsCache.getOrDefault(configName,
				new LinkedList<>());
		return serviceCache.stream().findAny();
	}

	public synchronized Optional<ApplicationConfiguration> findOne() throws ConfigurationServiceException {
        return staticConfigurationsCache.entrySet()
                .stream().findAny()
                .orElseThrow(() -> new ConfigurationServiceException("No configuration present"))
                .getValue()
                .stream()
                .findAny();
	}
	
	public Optional<ConfigurationServiceConfig> getServiceUrl(String configId) {
        ConfigurationServiceConfig ret = serviceConfigs.get(configId);

        if (ret == null && !serviceConfigs.isEmpty()) {
            ret = serviceConfigs.entrySet()
                    .stream().findAny()
                    .get().getValue();
        }
        return Optional.ofNullable(ret);
    }

	 public Optional<SecurePropertyPlaceholderModule> getSecurePropertyModule(String configId) {
	        ConfigurationServiceConfig ret = serviceConfigs.get(configId);
	        return Optional.ofNullable(ret.getSecurePropertyPlaceholderModule());
	    }
}
