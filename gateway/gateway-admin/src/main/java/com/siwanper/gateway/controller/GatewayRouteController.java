package com.siwanper.gateway.controller;

import com.siwanper.gateway.entity.form.GatewayRouteForm;
import com.siwanper.gateway.entity.form.GatewayRouteQueryForm;
import com.siwanper.gateway.entity.param.GatewayRouteQueryParam;
import com.siwanper.gateway.entity.po.GatewayRoute;
import com.siwanper.gateway.entity.vo.GatewayRouteVo;
import com.siwanper.gateway.service.GatewayRouteService;
import com.swp.ncloud.common.core.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gateway")
@Api("网关管理")
public class GatewayRouteController {

    @Autowired
    private GatewayRouteService gatewayRouteService;

    @ApiOperation(value = "添加网关")
    @ApiImplicitParam(name = "gatewayRouteForm", value = "添加网关信息", required = true, dataType = "GatewayRouteForm")
    @PostMapping
    public Result add(@Valid @RequestBody GatewayRouteForm gatewayRouteForm){
        GatewayRoute gatewayRoute = gatewayRouteForm.toPo(GatewayRoute.class);
        boolean result = gatewayRouteService.add(gatewayRoute);
        if (result) {
            return Result.success(gatewayRoute);
        } else {
            return Result.fail("添加失败");
        }
    }

    @ApiOperation(value = "删除网关")
    @ApiImplicitParam(name = "id", value = "删除网关信息", required = true, dataType = "Int", paramType = "Path")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        boolean result = gatewayRouteService.delete(id);
        if (result) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @ApiOperation("修改网关")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改网关信息", required = true, dataType = "Int", paramType = "Path"),
            @ApiImplicitParam(name = "gatewayRouteForm", value = "修嘎网关信息", required = true, dataType = "GatewayRouteForm")
    })
    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @Valid @RequestBody GatewayRouteForm gatewayRouteForm){
        GatewayRoute gatewayRoute = gatewayRouteForm.toPo(GatewayRoute.class);
        gatewayRoute.setId(id);
        boolean result = gatewayRouteService.update(gatewayRoute);
        if (result) {
            return Result.success("修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @ApiOperation(value = "根据Id网关")
    @ApiImplicitParam(name = "id", value = "根据ID网关信息", required = true, dataType = "Int", paramType = "Path")
    @GetMapping("/{id}")
    public Result get(@PathVariable int id){
        GatewayRoute gatewayRoute = this.gatewayRouteService.get(id);
        return Result.success(new GatewayRouteVo(gatewayRoute));
    }

    @ApiOperation(value = "根据网关url")
    @ApiImplicitParam(name = "url", value = "根据ID网关信息", required = true, dataType = "String", paramType = "param")
    @GetMapping
    public Result getByUri(@RequestParam String uri){
        List<GatewayRouteVo> gatewayRouteVos = this.gatewayRouteService.query(new GatewayRouteQueryParam(uri));
        return Result.success(gatewayRouteVos);
    }

    @ApiOperation("条件查询网关")
    @ApiImplicitParam(name = "gatewayRouteQueryForm", value = "条件查询网关表单", required = true, dataType = "GatewayRouteQueryForm")
    @PostMapping("/search")
    public Result query(@RequestBody GatewayRouteQueryForm gatewayRouteQueryForm){
        GatewayRouteQueryParam gatewayRouteQueryParam = gatewayRouteQueryForm.toParam(GatewayRouteQueryParam.class);
        List<GatewayRouteVo> gatewayRouteVos = this.gatewayRouteService.query(gatewayRouteQueryParam);
        return Result.success(gatewayRouteVos);
    }

    @ApiOperation("重新加载网关")
    @GetMapping("/overload")
    public Result overload(){
        boolean overload = this.gatewayRouteService.overload();
        return Result.success(overload);
    }


}
