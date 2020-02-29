package com.api.service.impl;

import com.api.core.service.AbstractService;
import com.api.dao.AppHistoryApiMapper;
import com.api.model.AppHistory;
import com.api.service.AppHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2019/11/14.
 */
@Service
@Transactional
public class AppHistoryServiceImpl extends AbstractService<AppHistory> implements AppHistoryService {
    @Resource
    private AppHistoryApiMapper appHistoryMapper;


    @Override
    public List<AppHistory> findeByAppId(Map<String, Object> params) {
        List<AppHistory> res = appHistoryMapper.selectByAppID(params);
        return res;
    }

    @Override
    public AppHistory findLast(Long appId) {
        AppHistory last = appHistoryMapper.findLast(appId);
        return last;
    }
}
