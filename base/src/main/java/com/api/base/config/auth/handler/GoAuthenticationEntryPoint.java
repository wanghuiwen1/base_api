package com.api.base.config.auth.handler;

import com.alibaba.fastjson.JSON;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 它负责启动未经过身份验证的用户的身份验证过程(当他们试图访问受保护的资源
 */
public class GoAuthenticationEntryPoint  implements AuthenticationEntryPoint {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        Result result = ResultGenerator.genForbiddenResult();
        logger.warn("身份验证出错",e);
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
        httpServletResponse.getWriter().flush();
    }

}
