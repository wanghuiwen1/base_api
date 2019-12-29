package com.api.code.generator.controller;


import com.api.code.generator.service.GeneratorService;
import com.api.core.annotation.PowerEnable;
import com.api.core.response.Result;
import com.api.core.response.ResultEnum;
import com.api.core.response.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
@PowerEnable(name = "生成代码", url = "/power")
@Api(value = "生成代码", tags = {"生成代码"})
@RestController
@RequestMapping("/generator")
public class CodeGeneratorController {
    @Resource
    private GeneratorService generatorService;


    @ApiOperation(value = "生成Model代码", tags = {"生成代码"}, notes = "生成Model代码")
    @GetMapping(value = "/model", name = "生成Model代码")
    public Result generatorCodeModel(@RequestParam String moduleName,
                                     @RequestParam String tableName,
                                     @RequestParam String businessName) {
        return generatorService.genCode(true,false, false,false, businessName, tableName, moduleName);
    }

    @ApiOperation(value = "生成Model和Mapper代码", tags = {"生成代码"}, notes = "生成Model和Mapper代码")
    @GetMapping(value = "/model/mapper", name = "生成Model和Mapper代码")
    public Result generatorCodeModelAndMapper(@RequestParam String moduleName,
                                              @RequestParam String tableName,
                                              @RequestParam String businessName) {
        generatorService.genCode(true, true,false,false, businessName, tableName, moduleName);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "生成service代码", tags = {"生成代码"}, notes = "生成service代码")
    @GetMapping(value = "/service", name = "生成service代码")
    public Result generatorCodeService(@RequestParam String moduleName,
                                              @RequestParam String tableName,
                                              @RequestParam String businessName) {
        return  generatorService.genCode(false, false,true,false, businessName, tableName, moduleName);
    }

    @ApiOperation(value = "生成controller代码", tags = {"生成代码"}, notes = "生成controller代码")
    @GetMapping(value = "/controller", name = "生成controller代码")
    public Result generatorCodeController(@RequestParam String moduleName,
                                       @RequestParam String tableName,
                                       @RequestParam String businessName) {


        return generatorService.genCode(false, false,false,true, businessName, tableName, moduleName);
    }

}
