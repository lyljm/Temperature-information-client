package org.com.RDcenter.model;


import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import java.util.Random;


/**
 * 包装mqtt连接参数
 */
public class MqttV3ConnectionParm {
    private String clientID;
    private MqttConnectOptions conOpts;//连接参数

//    public MqttV3ConnectionParm() {
//        // If the client ID was not set, generate one ourselves
//        this.clientID = randClienId(this.clientID);
//    }

    public MqttV3ConnectionParm(String clientID, MqttConnectOptions conOpts) {
        this.conOpts = conOpts;
        this.clientID = clientID;
        randClienId(clientID);
    }

    public String getHostURI() {
        return conOpts.getServerURIs()[0];
    }

    public String getClientID() {
        return clientID;
    }

    public MqttConnectOptions getConOpts() {
        return conOpts;
    }

    public boolean isAutomaticReconnectEnabled() {
        return conOpts.isAutomaticReconnect();
    }

    protected String randClienId(String clientID) {
        if (clientID == null || clientID.equals("")) {
            long uid = new Random().nextLong();
            clientID = "mqtt-client-test-" + uid;
        }
        return clientID;
    }
}
