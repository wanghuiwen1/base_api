package com.api.water.web.service;
import com.api.water.web.model.Follow;
import com.api.core.service.Service;
import com.api.core.response.Result;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
public interface FollowService extends Service<Follow> {
   Result list(String search, String order, Integer page, Integer size);
   Result add(HttpServletRequest request, Authentication authentication, Follow follow,String openid,String tradeType);
   Result checkSetting(Long docId);
   Result followList(Long userid, Byte type,Integer page,Integer size);
   List<Map> detail(Long followId);
   Result getChat(Long followId);
}
