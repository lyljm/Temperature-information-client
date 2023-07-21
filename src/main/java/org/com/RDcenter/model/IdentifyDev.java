package org.com.RDcenter.model;

import java.util.Objects;

public class IdentifyDev implements Comparable{

    public long time;
    public String id;
    public Device device;
    public String imei;
    public String imsi;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifyDev that = (IdentifyDev) o;
        return device.getDatanum() == that.device.getDatanum() && device.getMac().equals(that.device.getMac());
    }

    @Override
    public int hashCode() {
        return Objects.hash(device.getDatanum(),device.getMac());
    }

    @Override
    public int compareTo(Object o) {
        IdentifyDev object=(IdentifyDev) o;
        if(device.getDatanum() ==object.device.getDatanum()&&device.getMac().equals(object.device.getMac()))
            return 0;
        return (int)(this.time-object.time);
    }


    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", imei='" + imei + '\'' +
                ", imsi='" + imsi + '\'' +
                "," + device ;
    }
}
