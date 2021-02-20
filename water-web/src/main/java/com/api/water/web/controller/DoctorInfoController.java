package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.DoctorInfo;
import com.api.water.web.service.DoctorInfoService;
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
@PowerEnable(name = "医生信息",url = "/doctor/info")
@Api(value = "医生信息", tags = {"医生信息"})
@RestController
@RequestMapping("/doctor/info")
public class DoctorInfoController extends Ctrl{
    @Resource
    private DoctorInfoService doctorInfoService;

    @ApiOperation(value = "医生信息添加", tags = {"医生信息"}, notes = "医生信息添加")
    @PostMapping(value="/add",name="医生信息添加")
    public Result add(@ApiParam DoctorInfo doctorInfo) {
        doctorInfoService.save(doctorInfo);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医生信息删除", tags = {"医生信息"}, notes = "医生信息删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "医生信息id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="医生信息删除")
    public Result delete(@RequestParam Long id) {
        doctorInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医生信息修改", tags = {"医生信息"}, notes = "医生信息修改,对象主键必填")
    @PostMapping(value="/update",name="医生信息修改")
    public Result update(@ApiParam DoctorInfo doctorInfo) {
        doctorInfoService.update(doctorInfo);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医生信息详细信息", tags = {"医生信息"}, notes = "医生信息详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "医生信息id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="医生信息详细信息")
    public Result detail(@RequestParam Integer id) {
        DoctorInfo doctorInfo = doctorInfoService.findById(id);
        return ResultGenerator.genSuccessResult(doctorInfo);
    }

    @ApiOperation(value = "医生信息列表信息", tags = {"医生信息"}, notes = "医生信息列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "医生信息列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return doctorInfoService.list(search, order, page, size);
    }
}
