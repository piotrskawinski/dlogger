package com.dlogger.agent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
	private String hostname;
	private int port;    	
	private String indexType;

    public PropertyReader() throws Exception {
    	Properties properties = readProperties();
    	hostname = readHostFromProperty(properties);
    	port = readPortFromProperty(properties);    	
    	indexType = readTypeFromProperty(properties);            	
    }
    
    public String getHostname() {
		return hostname;
	}

	public int getPort() {
		return port;
	}

	public String getIndexType() {
		return indexType;
	}

	private Properties readProperties() throws IOException {
    	Properties properties = new Properties();
    	InputStream stream = null;
    	try {
    		// stream = getClass().getResourceAsStream("dlogger-appender.properties");
    		stream = PropertyReader.class.getClassLoader().getResourceAsStream("dlogger-appender.properties");
    		if (stream == null) {
    			throw new IOException("File dlogger-appender.properties not found");
    		}
    	    properties.load(stream);
    	    return properties;
    	} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
					// ignore
				}
			}
		}
    }
    
    private String readHostFromProperty(Properties properties) throws IOException {
    	return readProperty(properties, "log4j.appender.es.host");	    
    }
    
    private int readPortFromProperty(Properties properties) throws IOException {
    	return Integer.valueOf(readProperty(properties, "log4j.appender.es.port"));	    
    }
    
    private String readTypeFromProperty(Properties properties) throws IOException {
    	return readProperty(properties, "log4j.appender.es.type");	    
    }
    
    private String readProperty(Properties properties, String name) throws IOException {
    	String type = properties.getProperty(name);
	    if (type == null) {
	    	throw new IOException("Property " + name + " is missing.");
	    }
	    return type;
    }

}