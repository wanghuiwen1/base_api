package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.PatientCase;
import com.api.water.web.service.PatientCaseService;
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
@PowerEnable(name = "病历单",url = "/patient/case")
@Api(value = "病历单", tags = {"病历单"})
@RestController
@RequestMapping("/patient/case")
public class PatientCaseController extends Ctrl{
    @Resource
    private PatientCaseService patientCaseService;

    @ApiOperation(value = "病历单添加", tags = {"病历单"}, notes = "病历单添加")
    @PostMapping(value="/add",name="病历单添加")
    public Result add(@ApiParam PatientCase patientCase) {
        patientCaseService.save(patientCase);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "病历单删除", tags = {"病历单"}, notes = "病历单删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "病历单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="病历单删除")
    public Result delete(@RequestParam Long id) {
        patientCaseService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "病历单修改", tags = {"病历单"}, notes = "病历单修改,对象主键必填")
    @PostMapping(value="/update",name="病历单修改")
    public Result update(@ApiParam PatientCase patientCase) {
        patientCaseService.update(patientCase);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "病历单详细信息", tags = {"病历单"}, notes = "病历单详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "病历单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="病历单详细信息")
    public Result detail(@RequestParam Integer id) {
        PatientCase patientCase = patientCaseService.findById(id);
        return ResultGenerator.genSuccessResult(patientCase);
    }

    @ApiOperation(value = "病历单列表信息", tags = {"病历单"}, notes = "病历单列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "病历单列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return patientCaseService.list(search, order, page, size);
    }
}
