package com.api.base.config;

import com.api.base.model.SysPower;
import com.api.base.model.SysRole;
import com.api.base.service.SysPowerService;
import com.api.base.service.SysRoleService;
import com.api.core.annotation.PowerEnable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by wanghuiwen on 17-2-12.
 * 服务启动执行
 */
@Component
public class InitRunner implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysPowerService powerService;
    @Resource
    private SysRoleService roleService;

    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;


    public void run(String... strings) {
        initPower();
        initRole();
    }

    private void initRole() {
        for (SysRole r : ProjectConstant.initRole) {
            if (roleService.findBy("description", r.getDescription()) == null)
                roleService.save(r);
        }
    }

    private void initPower() {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            SysPower power = new SysPower();
            SysPower parent ;

            String parentName = "";
            String parentUrl = "";
            if (m.getValue().getMethod().getDeclaringClass().isAnnotationPresent(PowerEnable.class)) {
                parentName = m.getValue().getMethod().getDeclaringClass().getAnnotation(PowerEnable.class).name(); // 类名
                parentUrl = m.getValue().getMethod().getDeclaringClass().getAnnotation(PowerEnable.class).url(); // 类名

            }
            if (!StringUtils.isEmpty(parentName)) {
                parent = powerService.findBy("url", parentUrl);
                if (parent == null) {
                    parent = new SysPower();
                    parent.setName(parentName);
                    parent.setUrl(parentUrl);
                    parent.setPid(0);
                    powerService.save(parent);
                }
            }else {
                parent=null;
            }


            PatternsRequestCondition p = m.getKey().getPatternsCondition();
            for (String url : p.getPatterns()) {
                if (StringUtils.isEmpty(url)) continue;
                power.setUrl(url);
            }

            SysPower old = powerService.findBy("url", power.getUrl());
            power.setName(m.getKey().getName() == null ? power.getUrl() : m.getKey().getName());
            power.setPid(parent==null? -1 :parent.getId());
            if (old == null) {
                powerService.save(power);
            } else {
                old.setPid(parent==null? -1 :parent.getId());
                old.setName(m.getKey().getName());
                powerService.update(old);
            }
        }
    }


}
