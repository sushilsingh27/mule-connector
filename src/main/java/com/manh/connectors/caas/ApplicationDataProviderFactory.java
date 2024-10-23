package com.manh.connectors.caas;

import com.manh.connectors.caas.client.ClientUtils;
import com.manh.connectors.caas.client.DefaultApplicationDataProvider;
import javax.ws.rs.client.Client;

public class ApplicationDataProviderFactory {

    public ApplicationDataProvider newApplicationDataProvider(ServiceConfiguration config) {

            Client client = ClientUtils.buildRestClient(config);
            return new DefaultApplicationDataProvider(config.getServiceUrl(), client, config);

    }

}
