package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.Payment;
import com.api.water.web.service.PaymentService;
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
@PowerEnable(name = "订单",url = "/payment")
@Api(value = "订单", tags = {"订单"})
@RestController
@RequestMapping("/payment")
public class PaymentController extends Ctrl{
    @Resource
    private PaymentService paymentService;

    @ApiOperation(value = "订单添加", tags = {"订单"}, notes = "订单添加")
    @PostMapping(value="/add",name="订单添加")
    public Result add(@ApiParam Payment payment) {
        paymentService.save(payment);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "订单删除", tags = {"订单"}, notes = "订单删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "订单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="订单删除")
    public Result delete(@RequestParam Long id) {
        paymentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "订单修改", tags = {"订单"}, notes = "订单修改,对象主键必填")
    @PostMapping(value="/update",name="订单修改")
    public Result update(@ApiParam Payment payment) {
        paymentService.update(payment);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "订单详细信息", tags = {"订单"}, notes = "订单详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "订单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="订单详细信息")
    public Result detail(@RequestParam Integer id) {
        Payment payment = paymentService.findById(id);
        return ResultGenerator.genSuccessResult(payment);
    }

    @ApiOperation(value = "订单列表信息", tags = {"订单"}, notes = "订单列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "订单列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return paymentService.list(search, order, page, size);
    }
}
