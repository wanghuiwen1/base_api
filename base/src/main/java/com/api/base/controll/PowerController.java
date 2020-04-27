package com.api.base.controll;


import com.api.base.dto.ElTree;
import com.api.base.model.SysPower;
import com.api.base.service.SysPowerService;
import com.api.core.controller.Ctrl;
import com.api.core.annotation.PowerEnable;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
@PowerEnable(name = "权限管理",url = "/power")
@Api(value = "权限管理", tags = {"权限管理"})
@RestController
@RequestMapping("/power")
public class PowerController extends Ctrl {
    @Resource
    private SysPowerService sysPowerService;

    @ApiOperation(value = "权限树列表", tags = {"权限管理"}, notes = "权限树列表")
    @PostMapping(value = "/list", name = "权限树列表")
    public Result list() {
        List<SysPower> powers = sysPowerService.findAll();

        Map<Integer, List<SysPower>> res = powers.stream().collect(Collectors.groupingBy(SysPower::getPid));

        List<SysPower> parent = res.get(0);


        List<ElTree<SysPower>> elTrees = new ArrayList<>();

        for (SysPower p: parent) {
            ElTree<SysPower> elTree = new ElTree<>();
            elTree.setId(p.getId());
            elTree.setName(p.getName());
            elTree.setChildren(new ArrayList<>());
            elTrees.add(elTree);
        }

        for (Integer key:res.keySet()) {
            if(key!=0){
                for (ElTree<SysPower> e:elTrees) {
                    if(e.getId().equals(key)){
                       e.getChildren().addAll(res.get(key));
                    }
                }
            }
        }

        return ResultGenerator.genSuccessResult(elTrees);
    }
}
