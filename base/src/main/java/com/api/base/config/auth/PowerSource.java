package com.api.base.config.auth;

import com.api.base.model.Power;
import com.api.base.service.PowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class PowerSource implements FilterInvocationSecurityMetadataSource {
    Logger logger =  LoggerFactory.getLogger(this.getClass());

    private PowerService powerService;

//    @Autowired
//    private PowerService powerService1;

    public PowerSource(PowerService powerService) {
        this.powerService = powerService;
    }

    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法。
     * object-->FilterInvocation
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        List<Power> powers =powerService.findAll();
        FilterInvocation filterInvocation = (FilterInvocation) object;

        if (isMatcherAllowedRequest(filterInvocation)) return null ; //return null 表示允许访问，不做拦截
        HttpServletRequest request = filterInvocation.getHttpRequest();
        String resUrl;
        //URL规则匹配.
        AntPathRequestMatcher matcher;
        for(Power p : powers) {
            resUrl=p.getUrl();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return org.springframework.security.access.SecurityConfig.createList(p.getUrl());
            }
        }
        //没有有匹配到，需要指定相应的角色：
        return org.springframework.security.access.SecurityConfig.createList(filterInvocation.getRequestUrl());
    }




    /**
     * 判断当前请求是否在允许请求的范围内
     * @param fi 当前请求
     * @return 是否在范围中
     */
    private boolean isMatcherAllowedRequest(FilterInvocation fi){
        return allowedRequest().stream().map(AntPathRequestMatcher::new)
                .filter(requestMatcher -> requestMatcher.matches(fi.getHttpRequest()))
                .toArray().length > 0;
    }

    /**
     * 参考：
     * https://blog.csdn.net/pujiaolin/article/details/73928491
     * @return 定义允许请求的列表
     */
    private List<String> allowedRequest(){
        return Arrays.asList("/swagger-ui.html","/login","/page/**","/css/**","/fonts/**","/js/**","/scss/**",
                "/img/**","/static/**","/favicon.ico","/gettoken","/image/**","/webjars/springfox-swagger-ui/**",
                "/swagger-resources/**","/v2/api-docs/**","/user/get/openid","/patient/source/async","/payment/notify",
                "/user/refresh/token","/user/registered");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
