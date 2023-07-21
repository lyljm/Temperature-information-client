package org.com.RDcenter.Service.Imp;

import org.apache.ibatis.session.SqlSession;
import org.com.RDcenter.Service.ITempGunService;
import org.com.RDcenter.config.MybatisConfigLoader;
import org.com.RDcenter.mapper.ITempGunMapper;
import org.com.RDcenter.model.Gun;

import java.util.List;

public class TempGunService implements ITempGunService {
    @Override
    public List<Gun> getAll() {
        SqlSession sqlSession = MybatisConfigLoader.getSqlSession();
        ITempGunMapper recordMapper = sqlSession.getMapper(ITempGunMapper.class);
        List<Gun> guns = recordMapper.selectAll();
        sqlSession.close();
        return guns;
    }

    @Override
    public Gun getByMac(String mac) {
        SqlSession sqlSession = MybatisConfigLoader.getSqlSession();
        ITempGunMapper recordMapper = sqlSession.getMapper(ITempGunMapper.class);
        Gun gun = recordMapper.selectByMac(mac);
        sqlSession.close();
        return gun;
    }
}
