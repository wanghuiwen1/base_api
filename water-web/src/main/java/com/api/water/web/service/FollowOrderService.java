package com.api.water.web.service;
import com.api.water.web.model.FollowOrder;
import com.api.core.service.Service;
import com.api.core.response.Result;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
public interface FollowOrderService extends Service<FollowOrder> {
   Result list(Long id);
}
