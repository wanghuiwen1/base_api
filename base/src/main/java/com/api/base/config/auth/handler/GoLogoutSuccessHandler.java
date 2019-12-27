package com.api.base.config.auth.handler;

import com.alibaba.fastjson.JSON;
import com.api.core.response.Result;
import com.api.core.response.ResultEnum;
import com.api.core.response.ResultGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Result result = ResultGenerator.genResult(ResultEnum.CREATED);

        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
        httpServletResponse.getWriter().flush();

    }

}
