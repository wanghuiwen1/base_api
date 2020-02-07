package com.api.core.controller;

import com.api.common.JSONUtils;
import com.api.core.ServiceException;
import com.api.core.config.AuthUser;
import com.api.core.util.WhereLink;
import com.api.core.util.WhereProperty;
import org.springframework.security.core.Authentication;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public abstract class Ctrl {
    protected void buildWhere(String where, Example.Criteria criteria) {
        try {
            List<WhereProperty> whereLinks = JSONUtils.json2list(where, WhereProperty.class);

            for (WhereProperty m : whereLinks) {
                if (m.getWhereLink() == WhereLink.LIKE) {
                    criteria.andLike(m.getField(), m.getWhereLink().getSign() + (m.getValue()) + m.getWhereLink().getSign());
                } else {
                    criteria.andEqualTo(m.getField(), m.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    protected AuthUser getAuthUser(Authentication authentication){
        if(authentication == null) throw  new ServiceException("当前用户未登录");
        return (AuthUser) authentication.getPrincipal();
    }

}
