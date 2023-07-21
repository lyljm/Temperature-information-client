package org.com.RDcenter;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

import org.apache.ibatis.session.SqlSession;
import org.com.RDcenter.Service.Imp.TempGunService;
import org.com.RDcenter.config.DBconfig;
import org.com.RDcenter.config.MQTTConfig;
import org.com.RDcenter.mapper.ITempGunMapper;
import org.com.RDcenter.mapper.ITempRecordMapper;
import org.com.RDcenter.model.Device;
import org.com.RDcenter.config.MybatisConfigLoader;
import org.com.RDcenter.model.MqttConf;
import org.com.RDcenter.utils.CRCUtil;
import org.com.RDcenter.utils.DataHandler;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest {
//    @Test
//    public void testProperties() {
//        Properties p = new Properties();
//        try {
//            InputStream in = new FileInputStream("conf/ConnectionConfiguration.properties");
//            p.load(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    DataHandler dataHandler = new DataHandler();
//
//    @Test
//    public void testDataHandler() {
//        String[] value = {
//                "FDFD003CA32038363932393830353837313937303900000000B10108A70A00783939393939AAAAAAAA720100001434320C0F6E5101040064C5EAFEFE",
//                "FDFD003EA32038363932393830353838313031393300000000B101071F4F00783939393939AAAAAAAA72010000102A150C0FC85C00050C261AE0AF4CFEFE",
//                "FDFD003CA32038363932393830353837313937303900000000B10109CC5B003C3939393939AAAAAAAA720100000F24470C102064000A261888EAFEFE"
//        };
//        for (String i : value) {
//            Device device = dataHandler.parseData(i);
//            System.out.println(device.toString());
//        }
//    }

    @Test
    public void testSqlserver() {
//        SqlSession sqlSession = MybatisConfigLoader.getSqlSession();
//        ITempRecordMapper recordMapper = sqlSession.getMapper(ITempRecordMapper.class);
//        List<Device> devices = recordMapper.selectAll();
//        for (Device device : devices) {
//            System.out.println(device);
//        }
//        sqlSession.close();
    }

    @Test
    public void TestReadProperties() {
//        MQTTConfig config = new MQTTConfig();
//        System.out.println(MQTTConfig.clientID.equals(""));
    }

    @Test
    public void TestMapper() {
//    TempGunService service=new TempGunService();
//    service.getAll();
//    DBconfig.setProperties("1","1","1","1");
    }

//    @Test
//    public void Testreflect() {
//        MqttConf mqttConf=new MqttConf();
//        new MQTTConfig<MqttConf>().readConfig(mqttConf);
//        MqttConf mqttConf1=new MqttConf();
//        mqttConf1.qos="2";
//        mqttConf1.url="1";
//        mqttConf1.username="1";
//        mqttConf1.password="1";
//        mqttConf1.clientID="1";
//        mqttConf1.topic="1";
//        System.out.println(mqttConf.toString());
//        new MQTTConfig<MqttConf>().writeConfg(mqttConf1);
//    }
}
