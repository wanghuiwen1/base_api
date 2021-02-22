package com.api.water.web.service;
import com.api.water.web.model.FollowMessage;
import com.api.core.service.Service;
import com.api.core.response.Result;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
public interface FollowMessageService extends Service<FollowMessage> {
   Result send(FollowMessage followMessage,Byte type);

   Result read(Long followId,Long userId);

   Result close(Long followId, Authentication authentication);
   List<Map<String,Object>> getAllDoctorNoReadList();
}
