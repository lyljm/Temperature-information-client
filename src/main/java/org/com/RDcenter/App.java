package org.com.RDcenter;

import org.com.RDcenter.MQTTv3.Manager;
import org.com.RDcenter.UI.ConnectionDialog;
import org.com.RDcenter.UI.MainFrame;
import org.com.RDcenter.config.LogBackConfigLoader;
import org.com.RDcenter.config.MQTTConfig;
import org.com.RDcenter.config.MybatisConfigLoader;
import org.com.RDcenter.model.*;
import org.com.RDcenter.Service.DataHandlerService;


import javax.swing.*;

import java.io.IOException;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class App {
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
    }
}
