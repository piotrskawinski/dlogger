package com.dlogger.sample.test;

import java.net.InetAddress;

import org.apache.log4j.Logger;
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

public class DLogSampleAppenderTest {

    private Logger logger = Logger.getLogger(getClass());
    private static TransportClient client;

    @SuppressWarnings("resource")
    @BeforeClass
    public static void prepare() throws Exception {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
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
        logger.info(message);
        logger.info(message);
        logger.info(message);
        logger.info(message);
        logger.info(message);

        Thread.sleep(5 * 1000);

        // then
        response = query(message);
        Assert.assertEquals(5, response.getHits().getHits().length);
    }

    private String createMessage() {
        return String.valueOf(System.currentTimeMillis());
    }

    private SearchResponse query(String message) {
        return client.prepareSearch("dlog").setTypes("internal").setQuery(QueryBuilders.termQuery("message", message))
            .get();
    }
}
