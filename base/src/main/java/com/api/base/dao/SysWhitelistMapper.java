package com.api.base.dao;

import com.api.base.model.SysWhitelist;
import com.api.core.Mapper;
import com.api.core.response.Result;
import org.apache.ibatis.annotations.Param;

public interface SysWhitelistMapper extends Mapper<SysWhitelist> {
    void update(@Param("url") String url, @Param("id") String id);
}