package com.api.water.web.controller;
import com.api.core.config.AuthUser;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.FollowMessage;
import com.api.water.web.service.FollowMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.api.core.annotation.PowerEnable;
import io.swagger.annotations.*;



/**
* Created by wanghuiwen on 2021/01/20.
*/
@PowerEnable(name = "随访消息",url = "/follow/message")
@Api(value = "随访消息", tags = {"随访消息"})
@RestController
@RequestMapping("/follow/message")
public class FollowMessageController extends Ctrl{
    @Resource
    private FollowMessageService followMessageService;
    @Value("${web.upload-path}")
    private String path;
    @Value("${image.host}")
    private String host;

    @ApiOperation(value="随访发送消息",tags={"随访"},notes="随访发送消息")
    @PostMapping(value = "send",name = "随访发送消息")
    public Result send(@ApiParam FollowMessage followMessage, Authentication authentication){
        AuthUser authUser = (AuthUser)authentication.getPrincipal();
        Long time = System.currentTimeMillis() / 1000;
        followMessage.setCreateDate(time.intValue());
        followMessage.setSender(authUser.getId());
        return followMessageService.send(followMessage,authUser.getType());
    }

    @ApiOperation(value="随访消息设置已读",tags={"随访"},notes="随访消息设置已读")
    @ApiImplicitParams({
            @ApiImplicitParam(name="followId",value="随访ID",dataType="Long", paramType = "query"),
    })
    @PostMapping(value = "read",name = "随访消息设置已读")
    public Result read(Long followId, Authentication authentication){
        AuthUser authUser = (AuthUser)authentication.getPrincipal();
        return followMessageService.read(followId,authUser.getId());
    }
    @ApiOperation(value = "医生结束聊天", tags = {"随访"}, notes = "医生结束聊天")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "followId", value = "随访ID", dataType = "Long", paramType = "query"),
    })
    @PostMapping(value = "/close" ,name = "医生结束聊天")
    public Result close(Long followId, Authentication authentication) {
        return followMessageService.close(followId,authentication);
    }

    @ApiOperation(value = "所有医生未读列表", tags = {"随访"}, notes = "所有医生未读列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", dataType = "Integer", paramType = "query"),
    })
    @PostMapping(value = "/getAllDoctorNoReadList" ,name = "所有医生未读列表")
    public Result getAllDoctorNoReadList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> detail = followMessageService.getAllDoctorNoReadList();
        PageInfo pageInfo = new PageInfo<>(detail);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
