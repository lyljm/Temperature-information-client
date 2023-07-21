package org.com.RDcenter.MQTTv3;

import org.com.RDcenter.UI.MainFrame;
import org.com.RDcenter.model.MqttConf;
import org.com.RDcenter.model.MqttV3ConnectionParm;

/**
 * 通用项配置管理
 */
public class Manager {
    public static MqttV3ConnectionParm connectionParm;
    public static MqttV3Client client;
    public static boolean sqlConnection=false;
    public static boolean mqttConnection=false;
    public static MainFrame mainJFrame;
    public static MqttConf mqttConf;
}
