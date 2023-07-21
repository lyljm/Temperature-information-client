package org.com.RDcenter.mapper;

import org.apache.ibatis.annotations.Select;
import org.com.RDcenter.model.Device;
import org.com.RDcenter.model.Gun;

import java.util.List;

public interface ITempGunMapper {
    @Select("select * from TempGun")
    List<Gun> selectAll();

    @Select("select * from TempGun where GunMac=#{mac}")
    Gun selectByMac(String mac);
}
