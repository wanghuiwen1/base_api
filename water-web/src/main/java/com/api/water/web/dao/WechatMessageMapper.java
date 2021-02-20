package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.WechatMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WechatMessageMapper extends ApiMapper<WechatMessage> {
}