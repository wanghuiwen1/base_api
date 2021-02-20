package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.PrescriptionOrder;
import com.api.water.web.service.PrescriptionOrderService;
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
@PowerEnable(name = "处方订单",url = "/prescription/order")
@Api(value = "处方订单", tags = {"处方订单"})
@RestController
@RequestMapping("/prescription/order")
public class PrescriptionOrderController extends Ctrl{
    @Resource
    private PrescriptionOrderService prescriptionOrderService;

    @ApiOperation(value = "处方订单添加", tags = {"处方订单"}, notes = "处方订单添加")
    @PostMapping(value="/add",name="处方订单添加")
    public Result add(@ApiParam PrescriptionOrder prescriptionOrder) {
        prescriptionOrderService.save(prescriptionOrder);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "处方订单删除", tags = {"处方订单"}, notes = "处方订单删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "处方订单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="处方订单删除")
    public Result delete(@RequestParam Long id) {
        prescriptionOrderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "处方订单修改", tags = {"处方订单"}, notes = "处方订单修改,对象主键必填")
    @PostMapping(value="/update",name="处方订单修改")
    public Result update(@ApiParam PrescriptionOrder prescriptionOrder) {
        prescriptionOrderService.update(prescriptionOrder);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "处方订单详细信息", tags = {"处方订单"}, notes = "处方订单详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "处方订单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="处方订单详细信息")
    public Result detail(@RequestParam Integer id) {
        PrescriptionOrder prescriptionOrder = prescriptionOrderService.findById(id);
        return ResultGenerator.genSuccessResult(prescriptionOrder);
    }

    @ApiOperation(value = "处方订单列表信息", tags = {"处方订单"}, notes = "处方订单列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "处方订单列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return prescriptionOrderService.list(search, order, page, size);
    }
}
