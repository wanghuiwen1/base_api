package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.FollowOrder;
import com.api.water.web.service.FollowOrderService;
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
@PowerEnable(name = "随访订单",url = "/follow/order")
@Api(value = "随访订单", tags = {"随访订单"})
@RestController
@RequestMapping("/follow/order")
public class FollowOrderController extends Ctrl{
    @Resource
    private FollowOrderService followOrderService;

    @ApiOperation(value = "随访订单添加", tags = {"随访订单"}, notes = "随访订单添加")
    @PostMapping(value="/add",name="随访订单添加")
    public Result add(@ApiParam FollowOrder followOrder) {
        followOrderService.save(followOrder);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访订单删除", tags = {"随访订单"}, notes = "随访订单删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "随访订单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="随访订单删除")
    public Result delete(@RequestParam Long id) {
        followOrderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访订单修改", tags = {"随访订单"}, notes = "随访订单修改,对象主键必填")
    @PostMapping(value="/update",name="随访订单修改")
    public Result update(@ApiParam FollowOrder followOrder) {
        followOrderService.update(followOrder);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访订单详细信息", tags = {"随访订单"}, notes = "随访订单详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "随访订单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="随访订单详细信息")
    public Result detail(@RequestParam Integer id) {
        FollowOrder followOrder = followOrderService.findById(id);
        return ResultGenerator.genSuccessResult(followOrder);
    }

    @ApiOperation(value = "随访订单列表信息", tags = {"随访订单"}, notes = "随访订单列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "随访订单列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return followOrderService.list(search, order, page, size);
    }
}
