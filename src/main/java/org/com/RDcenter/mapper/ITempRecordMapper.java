package org.com.RDcenter.mapper;

import org.apache.ibatis.annotations.*;
import org.com.RDcenter.model.Device;

import java.util.List;


public interface ITempRecordMapper {
    @Insert("insert into TempRecord(type,datanum,cellarsn,gunsn," +
            "mac,sensornum,temp1,temp2,temp3,temp,humidity,o2,co2," +
            "h2s,pressure,liquidLevel,rssi,frequency," +
            "voltage,quantity,warningcode,imei,imsi,createTime)" +

            "values(#{type},#{datanum},#{cellarsn},#{gunsn}," +
            "#{mac},#{sensornum},#{temp1},#{temp2},#{temp3},#{temp},#{humidity},#{o2},#{co2}," +
            "#{h2s},#{pressure},#{liquidLevel},#{rssi},#{frequency}," +
            "#{voltage},#{quantity},#{warningcode},#{imei},#{imsi},#{createTime})")
     int insertDevice(Device device);

    @Select("select * from TempRecord")
     List<Device>selectAll();

    @Select("select * from TempRecord where gunsn=#{gunsn}")
     List<Device>selectByGunSn(String gunsn);

    @Select("select * from TempRecord where mac=#{mac}")
     List<Device>selectByMac(String mac);
}
