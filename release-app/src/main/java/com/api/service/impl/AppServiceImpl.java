package com.api.service.impl;

import com.api.common.GeneratorSnowflakeId;
import com.api.core.AbstractService;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.dao.AppMapper;
import com.api.model.App;
import com.api.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by CodeGenerator on 2019/11/14.
 */
@Service
@Transactional
public class AppServiceImpl extends AbstractService<App> implements AppService {
    Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Resource
    private AppMapper appMapper;
    @Value("${web.hostname}")
    private String host;

    @Override
    public Result add(App app, HttpServletRequest request) {
        app.setId(GeneratorSnowflakeId.nextId());
        app.setIndexUrl(host+"app/index/"+app.getId());
        appMapper.insertSelective(app);
        return ResultGenerator.genSuccessResult();
    }
}
