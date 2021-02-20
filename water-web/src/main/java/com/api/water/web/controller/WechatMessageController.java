package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.WechatMessage;
import com.api.water.web.service.WechatMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.List;
import com.api.core.annotation.PowerEnable;
import io.swagger.annotations.*;



/**
* Created by wanghuiwen on 2021/01/20.
*/
@PowerEnable(name = "微信消息",url = "/wechat/message")
@Api(value = "微信消息", tags = {"微信消息"})
@RestController
@RequestMapping("/wechat/message")
public class WechatMessageController extends Ctrl{
    @Resource
    private WechatMessageService wechatMessageService;

    @ApiOperation(value = "微信消息添加", tags = {"微信消息"}, notes = "微信消息添加")
    @PostMapping(value="/add",name="微信消息添加")
    public Result add(@ApiParam WechatMessage wechatMessage) {
        wechatMessageService.save(wechatMessage);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "微信消息删除", tags = {"微信消息"}, notes = "微信消息删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "微信消息id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="微信消息删除")
    public Result delete(@RequestParam Long id) {
        wechatMessageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "微信消息修改", tags = {"微信消息"}, notes = "微信消息修改,对象主键必填")
    @PostMapping(value="/update",name="微信消息修改")
    public Result update(@ApiParam WechatMessage wechatMessage) {
        wechatMessageService.update(wechatMessage);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "微信消息详细信息", tags = {"微信消息"}, notes = "微信消息详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "微信消息id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="微信消息详细信息")
    public Result detail(@RequestParam Integer id) {
        WechatMessage wechatMessage = wechatMessageService.findById(id);
        return ResultGenerator.genSuccessResult(wechatMessage);
    }

    @ApiOperation(value = "微信消息列表信息", tags = {"微信消息"}, notes = "微信消息列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "微信消息列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return wechatMessageService.list(search, order, page, size);
    }
}
