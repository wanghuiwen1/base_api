package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.Prescription;
import com.api.water.web.service.PrescriptionService;
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
@PowerEnable(name = "医嘱",url = "/prescription")
@Api(value = "医嘱", tags = {"医嘱"})
@RestController
@RequestMapping("/prescription")
public class PrescriptionController extends Ctrl{
    @Resource
    private PrescriptionService prescriptionService;

    @ApiOperation(value = "医嘱添加", tags = {"医嘱"}, notes = "医嘱添加")
    @PostMapping(value="/add",name="医嘱添加")
    public Result add(@ApiParam Prescription prescription) {
        prescriptionService.save(prescription);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医嘱删除", tags = {"医嘱"}, notes = "医嘱删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "医嘱id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="医嘱删除")
    public Result delete(@RequestParam Long id) {
        prescriptionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医嘱修改", tags = {"医嘱"}, notes = "医嘱修改,对象主键必填")
    @PostMapping(value="/update",name="医嘱修改")
    public Result update(@ApiParam Prescription prescription) {
        prescriptionService.update(prescription);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "医嘱详细信息", tags = {"医嘱"}, notes = "医嘱详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "医嘱id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="医嘱详细信息")
    public Result detail(@RequestParam Integer id) {
        Prescription prescription = prescriptionService.findById(id);
        return ResultGenerator.genSuccessResult(prescription);
    }

    @ApiOperation(value = "医嘱列表信息", tags = {"医嘱"}, notes = "医嘱列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "医嘱列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return prescriptionService.list(search, order, page, size);
    }
}
