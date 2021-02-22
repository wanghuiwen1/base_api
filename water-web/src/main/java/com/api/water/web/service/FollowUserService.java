package com.api.water.web.service;
import com.api.water.web.model.FollowUser;
import com.api.core.service.Service;
import com.api.core.response.Result;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
public interface FollowUserService extends Service<FollowUser> {
   Result list(String search, String order, Integer page, Integer size);
   void inspectors (Long doctorId,Long followId);
   Result getUserList(Long followId);

}
