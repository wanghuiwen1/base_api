package com.api.water.web.service;
import com.api.water.web.model.Follow;
import com.api.core.service.Service;
import com.api.core.response.Result;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
public interface FollowService extends Service<Follow> {
   Result list(String search, String order, Integer page, Integer size);
   Result add(HttpServletRequest request, Authentication authentication, Follow follow);
}
