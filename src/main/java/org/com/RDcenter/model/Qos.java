package org.com.RDcenter.model;

public enum Qos {
    Qos0(0), Qos1(1), Qos2(2);
    private int value;

    private Qos(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Qos getQos(int qos) {
        Qos q;
        switch (qos) {
            case 1:
                q = Qos.Qos1;
                break;
            case 2:
                q = Qos.Qos2;
                break;
            default:
                q = Qos.Qos0;
        }
        return q;
    }
}
