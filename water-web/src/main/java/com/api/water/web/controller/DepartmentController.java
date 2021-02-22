package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.Department;
import com.api.water.web.service.DepartmentService;
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
@PowerEnable(name = "科室",url = "/department")
@Api(value = "科室", tags = {"科室"})
@RestController
@RequestMapping("/department")
public class DepartmentController extends Ctrl{
    @Resource
    private DepartmentService departmentService;

    @ApiOperation(value = "科室添加", tags = {"科室"}, notes = "科室添加")
    @PostMapping(value="/add",name="科室添加")
    public Result add(@ApiParam Department department) {
        departmentService.save(department);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "科室删除", tags = {"科室"}, notes = "科室删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "科室id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="科室删除")
    public Result delete(@RequestParam Long id) {
        departmentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "科室修改", tags = {"科室"}, notes = "科室修改,对象主键必填")
    @PostMapping(value="/update",name="科室修改")
    public Result update(@ApiParam Department department) {
        departmentService.update(department);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "科室详细信息", tags = {"科室"}, notes = "科室详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "科室id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="科室详细信息")
    public Result detail(@RequestParam Integer id) {
        Department department = departmentService.findById(id);
        return ResultGenerator.genSuccessResult(department);
    }

    @ApiOperation(value = "科室列表信息", tags = {"科室"}, notes = "科室列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "科室列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return departmentService.list(search, order, page, size);
    }
}
