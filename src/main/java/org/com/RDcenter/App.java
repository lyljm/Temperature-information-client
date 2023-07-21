package org.com.RDcenter;

import org.com.RDcenter.MQTTv3.Manager;
import org.com.RDcenter.MQTTv3.MqttCallBackImp;
import org.com.RDcenter.UI.ConnectionDialog;
import org.com.RDcenter.UI.MainFrame;
import org.com.RDcenter.config.LogBackConfigLoader;
import org.com.RDcenter.MQTTv3.MqttV3Client;
import org.com.RDcenter.config.MQTTConfig;
import org.com.RDcenter.config.MybatisConfigLoader;
import org.com.RDcenter.model.*;
import org.com.RDcenter.Service.DataHandlerService;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class App {
    static MqttVersion mqttVersion = MqttVersion.v3;

    public static void main(String[] args) {

        /**
         * 加载Logger配置文件
         */
        try {
            LogBackConfigLoader.load("conf/logback.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //mqtt配置
        Manager.mqttConf = new MqttConf();
        new MQTTConfig<MqttConf>().readConfig(Manager.mqttConf);
        if(Manager.mqttConf.clientID.isEmpty()){
            Manager.mqttConf.clientID= ConnectionDialog.randomClientID();
        }

        //mybatis配置
        MybatisConfigLoader.reloadConfig();
        /**
         * todo
         * 初始化应用
         */
//        MqttConnectOptions conOpts = new MqttConnectOptions();
//        String[] urls = new String[]{"tcp://47.98.103.69:1883"};
//        conOpts.setServerURIs(urls);
//        conOpts.setUserName("canas");
//        conOpts.setPassword(new String("canas8888").toCharArray());
//        MqttV3ConnectionParm connectionParm = new MqttV3ConnectionParm("Liang", conOpts);

        /**
         * 订阅参数
         */
//        MqttV3SubscribeParm mqttV3SubscribeParm = new MqttV3SubscribeParm("LCX/JCReceive/#", Qos.Qos0);

        /**
         * 守护线程处理数据
         */
        Thread dataHandleDaemon = new Thread(DataHandlerService.getInstance());
        dataHandleDaemon.setDaemon(true);
        dataHandleDaemon.start();


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame=new MainFrame();
                DataHandlerService.getInstance().setShowMsg(mainFrame);
                Manager.mainJFrame=mainFrame;
                mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
                System.out.println("当前线程是否是 事件调度线程: " + SwingUtilities.isEventDispatchThread());
            }
        });

//        MqttCallback mqttCallback = new MqttCallBackImp();
//        MqttV3Client v3Executor = new MqttV3Client(mqttCallback, 1000);
//        v3Executor.connect(connectionParm);
//        v3Executor.subscribe(mqttV3SubscribeParm);



//        if (mqttVersion == MqttVersion.v3) {
//            MqttCallback mqttCallback=new MqttCallBackImp();
//            MqttV3Client v3Executor = new MqttV3Client(mqttCallback,1000);
//            v3Executor.execute(connectionParm,mqttV3SubscribeParm);
//        } else if (mqttVersion == MqttVersion.v5) {
////            MqttV5Executor v5Executor = new MqttV5Executor(line, mode, debug, quiet, actionTimeout);
////            v5Executor.execute();
//        }
    }
}
