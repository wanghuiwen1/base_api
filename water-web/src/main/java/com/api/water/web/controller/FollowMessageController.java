package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.FollowMessage;
import com.api.water.web.service.FollowMessageService;
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
@PowerEnable(name = "随访消息",url = "/follow/message")
@Api(value = "随访消息", tags = {"随访消息"})
@RestController
@RequestMapping("/follow/message")
public class FollowMessageController extends Ctrl{
    @Resource
    private FollowMessageService followMessageService;

    @ApiOperation(value = "随访消息添加", tags = {"随访消息"}, notes = "随访消息添加")
    @PostMapping(value="/add",name="随访消息添加")
    public Result add(@ApiParam FollowMessage followMessage) {
        followMessageService.save(followMessage);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访消息删除", tags = {"随访消息"}, notes = "随访消息删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "随访消息id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="随访消息删除")
    public Result delete(@RequestParam Long id) {
        followMessageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访消息修改", tags = {"随访消息"}, notes = "随访消息修改,对象主键必填")
    @PostMapping(value="/update",name="随访消息修改")
    public Result update(@ApiParam FollowMessage followMessage) {
        followMessageService.update(followMessage);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访消息详细信息", tags = {"随访消息"}, notes = "随访消息详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "随访消息id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="随访消息详细信息")
    public Result detail(@RequestParam Integer id) {
        FollowMessage followMessage = followMessageService.findById(id);
        return ResultGenerator.genSuccessResult(followMessage);
    }

    @ApiOperation(value = "随访消息列表信息", tags = {"随访消息"}, notes = "随访消息列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "随访消息列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return followMessageService.list(search, order, page, size);
    }
}
