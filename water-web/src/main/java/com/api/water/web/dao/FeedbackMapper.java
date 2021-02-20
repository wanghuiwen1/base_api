package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.Feedback;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbackMapper extends ApiMapper<Feedback> {
}