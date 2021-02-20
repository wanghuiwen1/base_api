package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.PrescriptionOrderDrug;
import com.api.water.web.service.PrescriptionOrderDrugService;
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
@PowerEnable(name = "处方药品",url = "/prescription/order/drug")
@Api(value = "处方药品", tags = {"处方药品"})
@RestController
@RequestMapping("/prescription/order/drug")
public class PrescriptionOrderDrugController extends Ctrl{
    @Resource
    private PrescriptionOrderDrugService prescriptionOrderDrugService;

    @ApiOperation(value = "处方药品添加", tags = {"处方药品"}, notes = "处方药品添加")
    @PostMapping(value="/add",name="处方药品添加")
    public Result add(@ApiParam PrescriptionOrderDrug prescriptionOrderDrug) {
        prescriptionOrderDrugService.save(prescriptionOrderDrug);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "处方药品删除", tags = {"处方药品"}, notes = "处方药品删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "处方药品id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="处方药品删除")
    public Result delete(@RequestParam Long id) {
        prescriptionOrderDrugService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "处方药品修改", tags = {"处方药品"}, notes = "处方药品修改,对象主键必填")
    @PostMapping(value="/update",name="处方药品修改")
    public Result update(@ApiParam PrescriptionOrderDrug prescriptionOrderDrug) {
        prescriptionOrderDrugService.update(prescriptionOrderDrug);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "处方药品详细信息", tags = {"处方药品"}, notes = "处方药品详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "处方药品id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="处方药品详细信息")
    public Result detail(@RequestParam Integer id) {
        PrescriptionOrderDrug prescriptionOrderDrug = prescriptionOrderDrugService.findById(id);
        return ResultGenerator.genSuccessResult(prescriptionOrderDrug);
    }

    @ApiOperation(value = "处方药品列表信息", tags = {"处方药品"}, notes = "处方药品列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "处方药品列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return prescriptionOrderDrugService.list(search, order, page, size);
    }
}
