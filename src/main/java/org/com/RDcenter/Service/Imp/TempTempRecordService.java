package org.com.RDcenter.Service.Imp;

import org.apache.ibatis.session.SqlSession;
import org.com.RDcenter.Service.ITempRecordService;
import org.com.RDcenter.Service.ITempGunService;
import org.com.RDcenter.config.MybatisConfigLoader;
import org.com.RDcenter.mapper.ITempRecordMapper;
import org.com.RDcenter.model.Device;
import org.com.RDcenter.model.Gun;
import org.com.RDcenter.model.IdentifyDev;

import java.time.LocalDateTime;
import java.util.List;

public class TempTempRecordService implements ITempRecordService {

    @Override
    public int insertDevice(IdentifyDev identifyDev) {
        Device device= identifyDev.device;
        device.setImei(identifyDev.imei);
        device.setImsi(identifyDev.imsi);

        ITempGunService tempGunService=new TempGunService();
        Gun gun = tempGunService.getByMac(device.getMac());

        SqlSession sqlSession = MybatisConfigLoader.getSqlSession();
        ITempRecordMapper recordMapper = sqlSession.getMapper(ITempRecordMapper.class);
        if(gun!=null){
            device.setGunsn(gun.getGunSN());
        }else {
            device.setGunsn("######");
        }
        device.setCreateTime(LocalDateTime.now());
        int res=recordMapper.insertDevice(device);
        sqlSession.commit();
        sqlSession.close();
        return res;
    }

    @Override
    public List<Device> selectAll() {
        SqlSession sqlSession = MybatisConfigLoader.getSqlSession();
        ITempRecordMapper recordMapper = sqlSession.getMapper(ITempRecordMapper.class);
        List<Device> devices = recordMapper.selectAll();
        sqlSession.close();
        return devices;
    }

    @Override
    public List<Device> selectByGunSn(String gunsn) {
        SqlSession sqlSession = MybatisConfigLoader.getSqlSession();
        ITempRecordMapper recordMapper = sqlSession.getMapper(ITempRecordMapper.class);
        List<Device> devices = recordMapper.selectByGunSn(gunsn);
        sqlSession.close();
        return devices;
    }

    @Override
    public List<Device> selectByMac(String mac) {
        SqlSession sqlSession = MybatisConfigLoader.getSqlSession();
        ITempRecordMapper recordMapper = sqlSession.getMapper(ITempRecordMapper.class);
        List<Device> devices = recordMapper.selectByGunSn(mac);
        sqlSession.close();
        return devices;
    }

}
