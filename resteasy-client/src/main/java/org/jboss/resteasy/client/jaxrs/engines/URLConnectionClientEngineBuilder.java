package org.jboss.resteasy.client.jaxrs.engines;

import org.jboss.resteasy.client.jaxrs.ClientHttpEngine;
import org.jboss.resteasy.client.jaxrs.ClientHttpEngineBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import java.util.concurrent.TimeUnit;

public class URLConnectionClientEngineBuilder implements ClientHttpEngineBuilder {

    private ResteasyClientBuilder resteasyClientBuilder;

    @Override
    public ClientHttpEngineBuilder resteasyClientBuilder(ResteasyClientBuilder resteasyClientBuilder) {
        this.resteasyClientBuilder = resteasyClientBuilder;
        return this;
    }

    @Override
    public ClientHttpEngine build() {
        URLConnectionEngine clientEngine = new URLConnectionEngine();

        if (resteasyClientBuilder.getReadTimeout(TimeUnit.MILLISECONDS) > -1)
        {
            clientEngine.setReadTimeout((int) resteasyClientBuilder.getReadTimeout(TimeUnit.MILLISECONDS));
        }
        if (resteasyClientBuilder.getConnectionTimeout(TimeUnit.MILLISECONDS) > -1)
        {
            clientEngine.setConnectTimeout((int) resteasyClientBuilder.getConnectionTimeout(TimeUnit.MILLISECONDS));
        }

        return clientEngine;
    }
}
