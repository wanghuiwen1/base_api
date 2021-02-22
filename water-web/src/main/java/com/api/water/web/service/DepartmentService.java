package com.api.water.web.service;
import com.api.water.web.model.Department;
import com.api.core.service.Service;
import com.api.core.response.Result;

/**
 * Created by wanghuiwen on 2021/02/22.
 */
public interface DepartmentService extends Service<Department> {
   Result list(String search, String order, Integer page, Integer size);
}
