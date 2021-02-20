package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.RefundApply;
import com.api.water.web.service.RefundApplyService;
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
@PowerEnable(name = "退款申请",url = "/refund/apply")
@Api(value = "退款申请", tags = {"退款申请"})
@RestController
@RequestMapping("/refund/apply")
public class RefundApplyController extends Ctrl{
    @Resource
    private RefundApplyService refundApplyService;

    @ApiOperation(value = "退款申请添加", tags = {"退款申请"}, notes = "退款申请添加")
    @PostMapping(value="/add",name="退款申请添加")
    public Result add(@ApiParam RefundApply refundApply) {
        refundApplyService.save(refundApply);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "退款申请删除", tags = {"退款申请"}, notes = "退款申请删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "退款申请id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="退款申请删除")
    public Result delete(@RequestParam Long id) {
        refundApplyService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "退款申请修改", tags = {"退款申请"}, notes = "退款申请修改,对象主键必填")
    @PostMapping(value="/update",name="退款申请修改")
    public Result update(@ApiParam RefundApply refundApply) {
        refundApplyService.update(refundApply);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "退款申请详细信息", tags = {"退款申请"}, notes = "退款申请详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "退款申请id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="退款申请详细信息")
    public Result detail(@RequestParam Integer id) {
        RefundApply refundApply = refundApplyService.findById(id);
        return ResultGenerator.genSuccessResult(refundApply);
    }

    @ApiOperation(value = "退款申请列表信息", tags = {"退款申请"}, notes = "退款申请列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "退款申请列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return refundApplyService.list(search, order, page, size);
    }
}
