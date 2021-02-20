package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.UserAddress;
import com.api.water.web.service.UserAddressService;
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
* Created by wanghuiwen on 2021/02/20.
*/
@PowerEnable(name = "用户地址",url = "/user/address")
@Api(value = "用户地址", tags = {"用户地址"})
@RestController
@RequestMapping("/user/address")
public class UserAddressController extends Ctrl{
    @Resource
    private UserAddressService userAddressService;

    @ApiOperation(value = "用户地址添加", tags = {"用户地址"}, notes = "用户地址添加")
    @PostMapping(value="/add",name="用户地址添加")
    public Result add(@ApiParam UserAddress userAddress) {
        userAddressService.save(userAddress);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "用户地址删除", tags = {"用户地址"}, notes = "用户地址删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "用户地址id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="用户地址删除")
    public Result delete(@RequestParam Long id) {
        userAddressService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "用户地址修改", tags = {"用户地址"}, notes = "用户地址修改,对象主键必填")
    @PostMapping(value="/update",name="用户地址修改")
    public Result update(@ApiParam UserAddress userAddress) {
        userAddressService.update(userAddress);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "用户地址详细信息", tags = {"用户地址"}, notes = "用户地址详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "用户地址id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="用户地址详细信息")
    public Result detail(@RequestParam Integer id) {
        UserAddress userAddress = userAddressService.findById(id);
        return ResultGenerator.genSuccessResult(userAddress);
    }

    @ApiOperation(value = "用户地址列表信息", tags = {"用户地址"}, notes = "用户地址列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "用户地址列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return userAddressService.list(search, order, page, size);
    }
}
