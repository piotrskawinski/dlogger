package com.dlogger.sample;

import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.dlogger.agent.PropertyReader;

@SpringBootApplication
class DLogSampleBootApplication {

    private static Logger logger = Logger.getLogger(DLogSampleBootApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DLogSampleBootApplication.class, args);
    }

    @SuppressWarnings("resource")
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            PropertyReader propertyReader = new PropertyReader();

            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                    new InetSocketTransportAddress(InetAddress.getByName(propertyReader.getHostname()),
                            propertyReader.getPort()));

            for (int i = 1; i < 100; i++) {
                logger.error("This is log message " + i);
            }

            client.close();
        };
    }

}
