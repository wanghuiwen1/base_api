package com.api.water.web.controller;
import com.api.core.config.AuthUser;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.Follow;
import com.api.water.web.service.FollowService;
import com.api.water.web.service.FollowUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import com.api.core.annotation.PowerEnable;
import io.swagger.annotations.*;



/**
* Created by wanghuiwen on 2021/01/20.
*/
@PowerEnable(name = "随访",url = "/follow")
@Api(value = "随访", tags = {"随访"})
@RestController
@RequestMapping("/follow")
public class FollowController extends Ctrl{
    @Resource
    private FollowService followService;
    @Resource
    private FollowUserService followUserService;

    @ApiOperation(value = "发起咨询", tags = {"随访"}, notes = "发起咨询")
    @PostMapping(value = "/add",name = "发起咨询")
    public Result add(HttpServletRequest request, Authentication authentication, @ApiParam Follow follow, String openid,  @RequestParam(defaultValue = "JSAPI") String tradeType) {
        return followService.add(request,authentication,follow,openid,tradeType);
    }
    @PostMapping(value = "/checkSetting",name = "检查设置")
    public Result checkSetting(Long docId) {
        return followService.checkSetting(docId);
    }



    @ApiOperation(value = "咨询列表", tags = {"随访"}, notes = "咨询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isvip", value = "是否是vip", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", dataType = "Integer", paramType = "query"),
    })
    @PostMapping(value = "/list" ,name = "咨询列表")
    public Result list(Authentication authentication,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        AuthUser authUser = (AuthUser)authentication.getPrincipal();
        return followService.followList(authUser.getId(), authUser.getType(),page,size);
    }

    @ApiOperation(value = "咨询详情", tags = {"随访"}, notes = "咨询详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "followId", value = "随访ID", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", dataType = "Integer", paramType = "query"),
    })
    @PostMapping(value = "/detail" ,name = "咨询详情")
    public Result detail(Long followId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        /*PageHelper.startPage(page, size);*/
        List<Map> detail = followService.detail(followId);
        /*PageInfo pageInfo = new PageInfo<>(detail);*/
        return ResultGenerator.genSuccessResult(detail);
    }

    @PostMapping(value = "/getChat" ,name = "获取聊天")
    public Result getChat(Long followId) {
        return followService.getChat(followId);
    }

    @ApiOperation(value = "获取当前群组的所有用户", tags = {"随访"}, notes = "获取当前群组的所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "followId", value = "随访ID", dataType = "Long", paramType = "query"),
    })
    @PostMapping(value = "/user/list" ,name = "获取当前群组的所有用户")
    public Result get(Long followId) {
        return followUserService.getUserList(followId);
    }

    @ApiOperation(value = "获取随访", tags = {"随访"}, notes = "获取随访")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "followId", value = "随访ID", dataType = "Long", paramType = "query"),
    })
    @PostMapping(value = "/get/by" ,name = "获取当前群组的所有用户")
    public Result getById(Long followId) {
        return ResultGenerator.genSuccessResult(followService.findById(followId));
    }
}
