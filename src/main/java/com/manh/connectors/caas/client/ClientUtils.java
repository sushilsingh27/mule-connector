package com.manh.connectors.caas.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.manh.connectors.caas.ServiceConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class ClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClientUtils.class);

    /**
     * Static instance of the allow all hostname verifier
     */
 
    public static Client buildRestClient(ServiceConfiguration config) {


        ClientBuilder cb = ClientBuilder.newBuilder();
        Client client = null;

        try {

                client = cb.build();
                return client;
        } catch (Throwable ex) {
            logger.error("Unknown error while building client...", ex);
        } finally {
            if (client != null) {
                client.register(JacksonJsonProvider.class);
            }
            return client;
        }
    }

}
