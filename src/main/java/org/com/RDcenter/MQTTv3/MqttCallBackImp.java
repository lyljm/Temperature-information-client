package org.com.RDcenter.MQTTv3;


import org.com.RDcenter.Service.DataHandlerService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.swing.*;

import static java.lang.Thread.sleep;


public class MqttCallBackImp implements MqttCallback {

    private DataHandlerService dataHandlerService=DataHandlerService.getInstance();

    public void connectionLost(Throwable throwable) {
        Manager.mainJFrame.setConLable(false);
        Manager.mqttConnection = false;
        while (true){
            try {
                Manager.client.reconnect();
            }catch (Exception e){
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            if(Manager.client.isConnection()){
                Manager.mainJFrame.setConLable(true);
                Manager.mqttConnection = true;
                Manager.mainJFrame.subscribeTopic();
                break;
            }
        }
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        dataHandlerService.messageArrived(s,mqttMessage);
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }

}
