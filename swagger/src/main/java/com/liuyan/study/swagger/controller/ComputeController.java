package com.liuyan.study.swagger.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyan on 2017/12/7.
 */
@Api(value = "计算服务", description = "简单的计算服务，提供加减乘除运算API")
@RestController
@RequestMapping("/compute")
public class ComputeController {
    @ApiOperation(value = "加法运算")
    @PostMapping("/add")
//    @ApiImplicitParams({@ApiImplicitParam(name = "a", value = "加数", required = true, dataType = "Double", paramType = "query"),
//            @ApiImplicitParam(name = "b", value = "加数", required = true, dataType = "Double", paramType = "query")})
    public Double add(@RequestParam @ApiParam(name = "a", value = "加数", required = true) Double a
            , @ApiParam(name = "b", value = "加数", required = true) @RequestParam Double b) {
        return a + b;
    }

    @ApiOperation(value = "减法运算")
    @PostMapping("/sub")
//    @ApiImplicitParams({@ApiImplicitParam(name = "a", value = "被减数", required = true, dataType = "Double", paramType = "query"),
//            @ApiImplicitParam(name = "b", value = "减数", required = true, dataType = "Double", paramType = "query")})
    public Double sub(@RequestParam @ApiParam(name = "a", value = "被减数", required = true) Double a,
                      @ApiParam(name = "b", value = "减数", required = true) @RequestParam Double b) {
        return a - b;
    }

    @ApiOperation(value = "乘法运算")
    @PostMapping("/mul")
//    @ApiImplicitParams({@ApiImplicitParam(name = "a", value = "被乘数", required = true, dataType = "Double", paramType = "query"),
//            @ApiImplicitParam(name = "b", value = "乘数", required = true, dataType = "Double", paramType = "query")})
    public Double mul(@RequestParam @ApiParam(name = "a", value = "被乘数", required = true) Double a,
                      @ApiParam(name = "b", value = "乘数", required = true) @RequestParam Double b) {
        return a * b;
    }

    @ApiOperation(value = "除法运算")
    @PostMapping("/div")
//    @ApiImplicitParams({@ApiImplicitParam(name = "a", value = "被除数", required = true, dataType = "Double", paramType = "query"),
//            @ApiImplicitParam(name = "b", value = "除数", required = true, dataType = "Double", paramType = "query")})
    public Double div(@ApiParam(name = "a", value = "被除数", required = true) @RequestParam Double a
            , @ApiParam(name = "b", value = "除数", required = true) @RequestParam Double b) {
        return a / b;
    }
}
