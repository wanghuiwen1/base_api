package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.FollowMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowMessageMapper extends ApiMapper<FollowMessage> {
    List<FollowMessage> getNoRead(@Param("userId") Long id);

    List<FollowMessage> getDoctorNoRead(@Param("doctorId") Long id);

    List<Map<String,Object>> getAllDoctorNoReadList();

    List<Map<String,Object>>getSomeOnesNoReadList(@Param("userId") Long id);
}