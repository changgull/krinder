package dev.changgull.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Base {
    private static Properties _properties;

    protected Properties getProperties() {
        return _properties;
    }

    protected void setProperties(Properties prop) {
        _properties = prop;
    }

    public void setProperty(String key, String value) {
        getProperties().setProperty(key, value);
    }

    public String getProperty(String key) {
        return getProperties().getProperty(key);
    }

    protected void loadProperties(String fileName) {
        try {
            // file should be located under resources folder
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            if (getProperties() == null) setProperties(new Properties());
            getProperties().load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }
}