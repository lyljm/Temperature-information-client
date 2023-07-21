package org.com.RDcenter.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MybatisConfigLoader {
    private static SqlSessionFactory sqlSessionFactory;

    public static void reloadConfig(){
        try {
            //获取 SqlSessionFactory对象
            String resource = "conf/mybatisConfig.xml";
            File file=new File(resource);
            InputStream inputStream =new FileInputStream(resource);
//                    Resources.getResourceAsStream(resource);
            Properties properties=new Properties();
            properties.load(new FileInputStream(new File("conf/jdbc.properties")));
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //SqlSessionFactory，从中获得 SqlSession 的实例。
    // SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
}
