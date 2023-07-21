package org.com.RDcenter.config;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

public class MQTTConfig<T> {
    File config = new File("conf/mqttConn.properties");
    Properties properties = new Properties();
    public void readConfig(T object) {
        try {
            properties.load(new FileInputStream(config));
            Class oClass = object.getClass();
            for (Field field : oClass.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(object, properties.getProperty(field.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void writeConfg(T object)  {
        try {
            properties.load(new FileInputStream(config));
            Class oClass = object.getClass();
            for (Field field : oClass.getDeclaredFields()) {
                field.setAccessible(true);
                properties.setProperty(field.getName(),(String)field.get(object));
            }
            properties.store(new FileOutputStream(config),null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
