package com.api.service;

import com.api.core.Service;
import com.api.core.response.Result;
import com.api.model.App;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by CodeGenerator on 2019/11/14.
 */
public interface AppService extends Service<App> {

    Result add(App app, HttpServletRequest request);
}
