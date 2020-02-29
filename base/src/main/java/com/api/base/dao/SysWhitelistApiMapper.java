package com.api.base.dao;

import com.api.base.model.SysWhitelist;
import com.api.core.ApiMapper;
import org.apache.ibatis.annotations.Param;

public interface SysWhitelistApiMapper extends ApiMapper<SysWhitelist> {
    void update(@Param("url") String url, @Param("id") String id);
}