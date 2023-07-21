package org.com.RDcenter.MQTTv3;

import org.com.RDcenter.model.MqttV3ConnectionParm;
import org.com.RDcenter.model.MqttV3SubscribeParm;
import org.com.RDcenter.model.Qos;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import static java.lang.Thread.sleep;


public class MqttV3Client {
    MqttV3ConnectionParm v3ConnectionParameters;

//    MqttV3PublishParm v3PublishParameters;

    MqttV3SubscribeParm v3SubscriptionParameters;

    MqttAsyncClient v3Client;

    MqttCallback mqttCallBack;

    private int actionTimeout;

    // To allow a graceful disconnect.
    final Thread mainThread = Thread.currentThread();

    static volatile boolean keepRunning = true;

    /**
     * 初始化客户端
     *
     * @param mqttCallBack
     * @param actionTimeout
     */
    public MqttV3Client(MqttCallback mqttCallBack, int actionTimeout) {
        this.mqttCallBack = new MqttCallBackImp();
        this.actionTimeout = actionTimeout;
    }

    public void connect(MqttV3ConnectionParm parm) {
        connect(parm, null);
    }
    public boolean isConnection(){
        return this.v3Client.isConnected();
    }

    /**
     * 连接注册回调
     *
     * @param listenerCallBack
     */
    public void connect(MqttV3ConnectionParm parm, IMqttActionListener listenerCallBack) {
        init(parm);
        IMqttToken connectToken = null;
        try {
            connectToken = v3Client.connect(v3ConnectionParameters.getConOpts(), null, listenerCallBack);
            // 超时指定它将阻塞的最长时间。如果操作在超时之前完成，则控制立即返回，否则它将阻塞，直到超时到期。
            connectToken.waitForCompletion(actionTimeout);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribe(MqttV3SubscribeParm mqttV3SubscribeParm) {
        subscribe(mqttV3SubscribeParm, null);
    }

    /**
     * 订阅
     *
     * @param mqttV3SubscribeParm 订阅参数
     * @param listenerCallBack    回调接口
     */
    public void subscribe(MqttV3SubscribeParm mqttV3SubscribeParm, IMqttActionListener listenerCallBack) {
        this.v3SubscriptionParameters = mqttV3SubscribeParm;
        try {
            IMqttToken subToken = this.v3Client.subscribe(this.v3SubscriptionParameters.getTopic(),
                    this.v3SubscriptionParameters.getQos().getValue(),
                    null,
                    listenerCallBack);
            subToken.waitForCompletion(actionTimeout);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void unSubscribe(String topic) {
        unSubscribe(topic, null);
    }

    public void unSubscribe(String topic, IMqttActionListener listenerCallback) {
        try {
            IMqttToken unsubscribe = this.v3Client.unsubscribe(topic, null, listenerCallback);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void reconnect() {
        try {
            this.v3Client.reconnect();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 命令行客户端执行，gui不要使用
     *
     * @param parm
     */
    public void execute(MqttV3ConnectionParm parm,MqttV3SubscribeParm mqttV3SubscribeParm) {
        try {
            connect(parm);
            addShutdownHook();
            subscribe(mqttV3SubscribeParm);
            while (keepRunning) {
                // Do nothing
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("?");
            disconnectClient();
            cmlCloseAndExit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用于连接时自动创建客户端
     */
    protected void init(MqttV3ConnectionParm parm) {
        this.v3ConnectionParameters = parm;
        try {
            this.v3Client = new MqttAsyncClient(this.v3ConnectionParameters.getHostURI(), this.v3ConnectionParameters.getClientID(), new MemoryPersistence());
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        this.v3Client.setCallback(mqttCallBack);
    }

    public void disconnectClient() {
        IMqttToken disconnectToken = null;
        try {
            disconnectToken = v3Client.disconnect();
            disconnectToken.waitForCompletion(actionTimeout);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnectClient(IMqttActionListener listenerCallBack) {
        IMqttToken disconnectToken = null;
        try {
            disconnectToken = v3Client.disconnect();
            disconnectToken.waitForCompletion(actionTimeout);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭客户端并不允许重连
     */
    public void closeClient() {
        try {
            this.v3Client.close();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 命令行关闭客户端和退出
     */
    private void cmlCloseAndExit() {
        closeClient();
        System.exit(0);
        try {
            mainThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a shutdown hook, that will gracefully disconnect the client when a
     * CTRL+C rolls in.
     */
    public void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                keepRunning = false;
            }
        });
    }
}