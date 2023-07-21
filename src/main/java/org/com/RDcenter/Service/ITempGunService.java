package org.com.RDcenter.Service;

import org.apache.ibatis.annotations.Select;
import org.com.RDcenter.model.Device;
import org.com.RDcenter.model.Gun;

import java.util.List;


public interface ITempGunService {

    List<Gun> getAll();

    Gun getByMac(String mac);

}
