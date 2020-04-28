package com.api.base.service.impl;

import com.api.base.dao.SysPowerMapper;
import com.api.base.model.SysPower;
import com.api.base.service.SysPowerService;
import com.api.common.mybatis.ResultMap;
import com.api.core.service.AbstractService;
import io.swagger.models.auth.In;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanghuiwen on 2020/04/27.
 */
@Service
@Transactional
public class SysPowerServiceImpl extends AbstractService<SysPower> implements SysPowerService {
    @Resource
    private SysPowerMapper sysPowerMapper;

    @Override
    @Cacheable(cacheNames = "power",key = "'all'")
    public List<SysPower> findAll() {
        return super.findAll();
    }

    @Override
    public List<ResultMap<String, Object>> listAll() {
        return sysPowerMapper.listAll();
    }

    @Override
    @Cacheable(cacheNames = "power",key = "#roleId")
    public List<SysPower>  getByRole(Integer roleId) {
        return sysPowerMapper.getByRole(roleId);
    }

    @Override
    @Cacheable(cacheNames = "user:power",key = "#roleId")
    public List<SysPower> getByUser(Long id) {
        return sysPowerMapper.getByUser(id);
    }
}
