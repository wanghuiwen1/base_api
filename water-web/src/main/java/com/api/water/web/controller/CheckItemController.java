package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.CheckItem;
import com.api.water.web.service.CheckItemService;
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
@PowerEnable(name = "检查项",url = "/check/item")
@Api(value = "检查项", tags = {"检查项"})
@RestController
@RequestMapping("/check/item")
public class CheckItemController extends Ctrl{
    @Resource
    private CheckItemService checkItemService;

    @ApiOperation(value = "检查项添加", tags = {"检查项"}, notes = "检查项添加")
    @PostMapping(value="/add",name="检查项添加")
    public Result add(@ApiParam CheckItem checkItem) {
        checkItemService.save(checkItem);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "检查项删除", tags = {"检查项"}, notes = "检查项删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "检查项id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="检查项删除")
    public Result delete(@RequestParam Long id) {
        checkItemService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "检查项修改", tags = {"检查项"}, notes = "检查项修改,对象主键必填")
    @PostMapping(value="/update",name="检查项修改")
    public Result update(@ApiParam CheckItem checkItem) {
        checkItemService.update(checkItem);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "检查项详细信息", tags = {"检查项"}, notes = "检查项详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "检查项id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="检查项详细信息")
    public Result detail(@RequestParam Integer id) {
        CheckItem checkItem = checkItemService.findById(id);
        return ResultGenerator.genSuccessResult(checkItem);
    }

    @ApiOperation(value = "检查项列表信息", tags = {"检查项"}, notes = "检查项列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "检查项列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return checkItemService.list(search, order, page, size);
    }
}
