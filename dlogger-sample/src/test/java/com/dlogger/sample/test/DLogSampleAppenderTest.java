package com.dlogger.sample.test;

import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dlogger.agent.PropertyReader;

public class DLogSampleAppenderTest {

    private Logger logger = Logger.getLogger(getClass());

    private static PropertyReader propertyReader;
    private static TransportClient client;

    @SuppressWarnings("resource")
    @BeforeClass
    public static void prepare() throws Exception {
        propertyReader = new PropertyReader();

        client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new InetSocketTransportAddress(
                InetAddress.getByName(propertyReader.getHostname()), propertyReader.getPort()));

        deleteOldDocuments();

        Thread.sleep(2 * 1000);
    }

    @AfterClass
    public static void cleanup() {
        client.close();
    }

    @Test
    public void logMessages() throws Exception {
        // prepare message to log
        String message = createMessage();

        // pre - assert
        SearchResponse response = query(message);
        Assert.assertEquals(0, response.getHits().getHits().length);

        // when
        logger.error(message);
        logger.error(message);
        logger.error(message);
        logger.error(message);
        logger.error(message);

        Thread.sleep(2 * 1000);

        // then
        response = query(message);
        Assert.assertEquals(5, response.getHits().getHits().length);
    }

    private String createMessage() {
        return "Simple message for test";
    }

    private SearchResponse query(String message) {
        return client.prepareSearch("dlog").setTypes(propertyReader.getIndexType())
                .setQuery(QueryBuilders.matchQuery("message", message)).get();
    }

    private static void deleteOldDocuments() {
        SearchResponse response = client.prepareSearch("dlog")
                .setTypes(propertyReader.getIndexType())
                .setSize(1000)
                .get();

        response.getHits().forEach(hit -> {
            try {
                client.prepareDelete("dlog", propertyReader.getIndexType(), hit.getId()).get();
                logStdout("Deleted index with id: " + hit.getId());

            } catch (ElasticsearchException e) {
                logStdout("Failed deleting index: " + e.getMessage());
            }
        });
    }

    private static void logStdout(String message) {
        System.out.println(message);
    }
}
