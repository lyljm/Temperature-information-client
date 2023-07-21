package org.com.RDcenter.config;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import java.io.*;
import java.util.Properties;

public class DBconfig {
    public static String driver;
    public static String url;
    public static String username;
    public static String password;

    static {
        File config = new File("conf/jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(config));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public static void setProperties(String pdriver, String purl, String pusername, String ppassword) {
        File config = new File("conf/jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(config));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (pdriver != null) {
            properties.setProperty("driver", pdriver);
            driver = pdriver;
        }
        if (purl != null) {
            properties.setProperty("url", purl);
            url = purl;
        }
        if (pusername != null) {
            properties.setProperty("username", pusername);
            username = pusername;
        }

        if (ppassword != null) {
            properties.setProperty("password", ppassword);
            password = ppassword;
        }
        try {
            properties.store(new FileOutputStream(config), null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void isConnection() throws Exception {
        SqlSession sqlSession = null;
        sqlSession = MybatisConfigLoader.getSqlSession();
        sqlSession.getConnection();
    }

}
