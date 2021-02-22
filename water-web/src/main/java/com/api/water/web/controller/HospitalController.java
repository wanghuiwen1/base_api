package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.Hospital;
import com.api.water.web.service.HospitalService;
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
* Created by wanghuiwen on 2021/02/22.
*/
@PowerEnable(name = "医院",url = "/hospital")
@Api(value = "医院", tags = {"医院"})
@RestController
@RequestMapping("/hospital")
public class HospitalController extends Ctrl{
    @Resource
    private HospitalService hospitalService;

    @ApiOperation(value = "医院添加", tags = {"医院"}, notes = "医院添加")
    @PostMapping(value="/add",name="医院添加")
    public Result add(@ApiParam Hospital hospital) {
        hospitalService.save(hospital);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医院删除", tags = {"医院"}, notes = "医院删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "医院id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="医院删除")
    public Result delete(@RequestParam Long id) {
        hospitalService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医院修改", tags = {"医院"}, notes = "医院修改,对象主键必填")
    @PostMapping(value="/update",name="医院修改")
    public Result update(@ApiParam Hospital hospital) {
        hospitalService.update(hospital);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医院详细信息", tags = {"医院"}, notes = "医院详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "医院id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="医院详细信息")
    public Result detail(@RequestParam Integer id) {
        Hospital hospital = hospitalService.findById(id);
        return ResultGenerator.genSuccessResult(hospital);
    }

    @ApiOperation(value = "医院列表信息", tags = {"医院"}, notes = "医院列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "医院列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return hospitalService.list(search, order, page, size);
    }
}
