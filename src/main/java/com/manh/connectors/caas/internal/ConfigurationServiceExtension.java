package com.manh.connectors.caas.internal;

import com.manh.connectors.caas.internal.operations.ReadPropertiesOperation;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Export;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

@Xml(prefix = "manh-caas")
@Extension(name = "MANH Configuration Service")
@Configurations(ConfigurationServiceConfig.class)
@Operations(ReadPropertiesOperation.class)
@Export(resources = "META-INF/services/org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProviderFactory")
public class ConfigurationServiceExtension {
}
