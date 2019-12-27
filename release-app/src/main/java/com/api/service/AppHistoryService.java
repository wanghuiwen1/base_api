package com.api.service;
import com.api.core.service.Service;
import com.api.model.AppHistory;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2019/11/14.
 */
public interface AppHistoryService extends Service<AppHistory> {

    List<AppHistory> findeByAppId(Map<String, Object> params);

    AppHistory findLast(Long appId);
}
