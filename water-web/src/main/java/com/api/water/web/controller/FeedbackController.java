package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.Feedback;
import com.api.water.web.service.FeedbackService;
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
@PowerEnable(name = "意见反馈",url = "/feedback")
@Api(value = "意见反馈", tags = {"意见反馈"})
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends Ctrl{
    @Resource
    private FeedbackService feedbackService;

    @ApiOperation(value = "意见反馈添加", tags = {"意见反馈"}, notes = "意见反馈添加")
    @PostMapping(value="/add",name="意见反馈添加")
    public Result add(@ApiParam Feedback feedback) {
        feedbackService.save(feedback);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "意见反馈删除", tags = {"意见反馈"}, notes = "意见反馈删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "意见反馈id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="意见反馈删除")
    public Result delete(@RequestParam Long id) {
        feedbackService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "意见反馈修改", tags = {"意见反馈"}, notes = "意见反馈修改,对象主键必填")
    @PostMapping(value="/update",name="意见反馈修改")
    public Result update(@ApiParam Feedback feedback) {
        feedbackService.update(feedback);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "意见反馈详细信息", tags = {"意见反馈"}, notes = "意见反馈详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "意见反馈id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="意见反馈详细信息")
    public Result detail(@RequestParam Integer id) {
        Feedback feedback = feedbackService.findById(id);
        return ResultGenerator.genSuccessResult(feedback);
    }

    @ApiOperation(value = "意见反馈列表信息", tags = {"意见反馈"}, notes = "意见反馈列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "意见反馈列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return feedbackService.list(search, order, page, size);
    }
}
