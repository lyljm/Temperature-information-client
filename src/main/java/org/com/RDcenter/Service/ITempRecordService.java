package org.com.RDcenter.Service;

import org.com.RDcenter.model.Device;
import org.com.RDcenter.model.IdentifyDev;

import java.util.List;

public interface ITempRecordService {

    int insertDevice(IdentifyDev device);

    List<Device>selectAll();

    List<Device>selectByGunSn(String gunsn);

    List<Device>selectByMac(String mac);

}
