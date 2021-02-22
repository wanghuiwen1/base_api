package com.api.water.web.service;
import com.api.water.web.model.Hospital;
import com.api.core.service.Service;
import com.api.core.response.Result;

/**
 * Created by wanghuiwen on 2021/02/22.
 */
public interface HospitalService extends Service<Hospital> {
   Result list(String search, String order, Integer page, Integer size);
}
