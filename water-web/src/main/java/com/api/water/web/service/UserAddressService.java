package com.api.water.web.service;
import com.api.water.web.model.UserAddress;
import com.api.core.service.Service;
import com.api.core.response.Result;

/**
 * Created by wanghuiwen on 2021/02/20.
 */
public interface UserAddressService extends Service<UserAddress> {
   Result list(String search, String order, Integer page, Integer size);
}
