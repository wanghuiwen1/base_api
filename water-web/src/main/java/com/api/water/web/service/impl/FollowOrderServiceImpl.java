package com.api.water.web.service.impl;

import com.api.water.web.dao.FollowOrderMapper;
import com.api.water.web.model.FollowOrder;
import com.api.water.web.service.FollowOrderService;
import com.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.api.common.JSONUtils;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
@Service
@Transactional
public class FollowOrderServiceImpl extends AbstractService<FollowOrder> implements FollowOrderService {
    @Resource
    private FollowOrderMapper followOrderMapper;


    @Override
    public Result list(Long id) {
        List<Map<String,Object>> res = followOrderMapper.list(id);

        return ResultGenerator.genSuccessResult(res);
    }
}
