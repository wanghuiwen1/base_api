package com.api.water.web.controller;
import com.api.core.config.AuthUser;
import com.api.core.controller.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.water.web.model.FollowOrder;
import com.api.water.web.service.FollowOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
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

    @ApiOperation(value="随访订单详细信息",tags={"随访"},notes="随访订单详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="随访订单ID",dataType="Long", paramType = "query"),
    })
    @PostMapping(value = "/detail",name = "随访订单详细信息")
    public Result detail(@RequestParam Integer id) {
        FollowOrder followOrder = followOrderService.findById(id);
        return ResultGenerator.genSuccessResult(followOrder);
    }
    @ApiOperation(value="随访订单列表",tags={"随访"},notes="随访订单列表")
    @PostMapping(value = "/list",name = "随访订单详细信息")
    public Result list(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return followOrderService.list(authUser.getId());
    }
}
