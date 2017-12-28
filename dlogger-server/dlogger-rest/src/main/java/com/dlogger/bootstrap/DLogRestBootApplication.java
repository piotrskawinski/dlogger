package com.dlogger.bootstrap;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.dlogger")
@EnableElasticsearchRepositories(basePackages = "com.dlogger.repository.elasticsearch")
@EntityScan("com.dlogger")
@ComponentScan("com.dlogger")
@SpringBootApplication
public class DLogRestBootApplication {

    @SuppressWarnings("resource")
    @Bean
    public Client client() throws Exception {
        return new PreBuiltTransportClient(Settings.EMPTY)
            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

    public static void main(String[] args) {
        SpringApplication.run(DLogRestBootApplication.class, args);
    }

}
