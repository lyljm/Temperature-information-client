package org.com.RDcenter.model;

public class MqttV3SubscribeParm {
    private String topic;

    private Qos qos;

    private boolean verbose = false;

    public MqttV3SubscribeParm(String topic, Qos qos) {
        this.topic = topic;
        this.qos = qos;
    }

    public MqttV3SubscribeParm(String topic, Qos qos, boolean verbose) {
        this.topic = topic;
        this.qos = qos;
        this.verbose = verbose;
    }

    public String getTopic() {
        return topic;
    }

    public Qos getQos() {
        return qos;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setQos(Qos qos) {
        this.qos = qos;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
