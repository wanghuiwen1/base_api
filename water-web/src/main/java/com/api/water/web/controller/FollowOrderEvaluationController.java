package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.FollowOrderEvaluation;
import com.api.water.web.service.FollowOrderEvaluationService;
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
@PowerEnable(name = "随访评价",url = "/follow/order/evaluation")
@Api(value = "随访评价", tags = {"随访评价"})
@RestController
@RequestMapping("/follow/order/evaluation")
public class FollowOrderEvaluationController extends Ctrl{
    @Resource
    private FollowOrderEvaluationService followOrderEvaluationService;

    @ApiOperation(value = "随访评价添加", tags = {"随访评价"}, notes = "随访评价添加")
    @PostMapping(value="/add",name="随访评价添加")
    public Result add(@ApiParam FollowOrderEvaluation followOrderEvaluation) {
        followOrderEvaluationService.save(followOrderEvaluation);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访评价删除", tags = {"随访评价"}, notes = "随访评价删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "随访评价id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="随访评价删除")
    public Result delete(@RequestParam Long id) {
        followOrderEvaluationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访评价修改", tags = {"随访评价"}, notes = "随访评价修改,对象主键必填")
    @PostMapping(value="/update",name="随访评价修改")
    public Result update(@ApiParam FollowOrderEvaluation followOrderEvaluation) {
        followOrderEvaluationService.update(followOrderEvaluation);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访评价详细信息", tags = {"随访评价"}, notes = "随访评价详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "随访评价id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="随访评价详细信息")
    public Result detail(@RequestParam Integer id) {
        FollowOrderEvaluation followOrderEvaluation = followOrderEvaluationService.findById(id);
        return ResultGenerator.genSuccessResult(followOrderEvaluation);
    }

    @ApiOperation(value = "随访评价列表信息", tags = {"随访评价"}, notes = "随访评价列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "随访评价列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return followOrderEvaluationService.list(search, order, page, size);
    }
}
