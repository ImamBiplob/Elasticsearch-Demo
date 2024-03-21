package com.imambiplob.elasticsearchdemo.config;

import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class ImperativeElasticsearchConfig extends ElasticsearchConfiguration {
    @Override
    public ClientConfiguration clientConfiguration() {
        try {
            SSLContext sslContext = SSLContextBuilder
                    .create()
                    .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                    .build();

            return ClientConfiguration.builder()
                    .connectedTo("172.18.0.2:9200").usingSsl(sslContext)
                    .withBasicAuth("elastic", "pP2g-x5wpMdO5GDBHBIU")
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }
}
