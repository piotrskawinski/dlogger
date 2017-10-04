package com.dlogger.agent;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class DLogAppender extends AppenderSkeleton {

    private TransportClient client;

    @SuppressWarnings("resource")
    public DLogAppender() throws Exception {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    }

    @Override
    public void append(LoggingEvent event) {
        try {
            IndexResponse response = client.prepareIndex("dlog", "internal")
                .setSource(XContentFactory.jsonBuilder().startObject().field("message", event.getMessage())
                .field("level", event.getLevel().toString())
                .field("timestamp", new Date(event.getTimeStamp())).endObject())
                .get();

            log("Response -> " + response.toString());

        } catch (IOException e) {
            log("Failed saving log message -> " + e.getMessage());
        }
    }

    @Override
    public void close() {
        this.closed = true;
        this.client.close();
        log("DLog appender closed");
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    private static void log(String message) {
        System.out.println(message);
    }

}