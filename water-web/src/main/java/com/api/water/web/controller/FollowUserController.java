package com.api.water.web.controller;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.FollowUser;
import com.api.water.web.service.FollowUserService;
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
@PowerEnable(name = "随访人员",url = "/follow/user")
@Api(value = "随访人员", tags = {"随访人员"})
@RestController
@RequestMapping("/follow/user")
public class FollowUserController extends Ctrl{
    @Resource
    private FollowUserService followUserService;

    @ApiOperation(value = "随访人员添加", tags = {"随访人员"}, notes = "随访人员添加")
    @PostMapping(value="/add",name="随访人员添加")
    public Result add(@ApiParam FollowUser followUser) {
        followUserService.save(followUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访人员删除", tags = {"随访人员"}, notes = "随访人员删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "随访人员id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="随访人员删除")
    public Result delete(@RequestParam Long id) {
        followUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访人员修改", tags = {"随访人员"}, notes = "随访人员修改,对象主键必填")
    @PostMapping(value="/update",name="随访人员修改")
    public Result update(@ApiParam FollowUser followUser) {
        followUserService.update(followUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "随访人员详细信息", tags = {"随访人员"}, notes = "随访人员详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "随访人员id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="随访人员详细信息")
    public Result detail(@RequestParam Integer id) {
        FollowUser followUser = followUserService.findById(id);
        return ResultGenerator.genSuccessResult(followUser);
    }

    @ApiOperation(value = "随访人员列表信息", tags = {"随访人员"}, notes = "随访人员列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "search", value = "查询条件json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "order", value = "排序json", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query", defaultValue = "10")
    })
    @PostMapping(value = "/list", name = "随访人员列表信息")
    public Result list(@RequestParam(defaultValue = "{}") String search,
                       @RequestParam(defaultValue = "{}") String order,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return followUserService.list(search, order, page, size);
    }
}
