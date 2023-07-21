package org.com.RDcenter.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Device {
    private String imei;
    private String imsi;
    private int flag;
    private int type;
    private int datanum;
    private int rssi;
    private int frequency;
    private String cellarsn;
    private String gunsn;
    private String mac;
    private int voltage;
    private int quantity;
    private int warningcode;
    private int sensornum;
    private double temp1;
    private double temp2;
    private double temp3;
    private double temp;
    private double humidity;
    private double o2;
    private double co2;
    private double h2s;
    private double pressure;
    private double liquidLevel;
    private String version;
    private LocalDateTime createTime;
}
