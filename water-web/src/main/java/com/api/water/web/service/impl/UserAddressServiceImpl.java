package com.api.water.web.service.impl;

import com.api.water.web.dao.UserAddressMapper;
import com.api.water.web.model.UserAddress;
import com.api.water.web.service.UserAddressService;
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
 * Created by wanghuiwen on 2021/02/20.
 */
@Service
@Transactional
public class UserAddressServiceImpl extends AbstractService<UserAddress> implements UserAddressService {
    @Resource
    private UserAddressMapper userAddressMapper;

    @Override
    public Result list(String search, String order, Integer page, Integer size){
        Map<String, Object> params = JSONUtils.json2map(search);
        Map<String, Object> orderParams = JSONUtils.json2map(order);
        for (String key : orderParams.keySet()) {
                if (orderParams.get(key) != null && orderParams.get(key).equals("ascending")) orderParams.put(key, "asc");
                if (orderParams.get(key) != null && orderParams.get(key).equals("descending")) orderParams.put(key, "desc");
            }
        PageHelper.startPage(page, size);
        List<Map<String, Object>> res = userAddressMapper.baseList(params, orderParams);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
