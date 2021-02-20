package com.api.water.web.service;
import com.api.water.web.model.FollowSetting;
import com.api.core.service.Service;
import com.api.core.response.Result;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
public interface FollowSettingService extends Service<FollowSetting> {
   Result list(String search, String order, Integer page, Integer size);
}
