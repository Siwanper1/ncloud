package com.swp.organization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.organization.entity.form.MenuForm;
import com.swp.organization.entity.form.MenuQueryForm;
import com.swp.organization.entity.form.MenuUpdateForm;
import com.swp.organization.entity.param.MenuQueryParam;
import com.swp.organization.entity.po.Menu;
import com.swp.organization.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@Api("菜单管理")
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "添加菜单")
    @ApiImplicitParam(name = "menuForm", value = "菜单表单信息", required = true, dataType = "MenuForm")
    @PostMapping
    public Result add(@Valid @RequestBody MenuForm menuForm){
        Menu menu = menuForm.toPo(Menu.class);
        boolean result = menuService.add(menu);
        if (result) {
            return Result.success(menu);
        } else {
            return Result.fail("添加失败，请重试");
        }
    }

    @ApiOperation(value = "删除菜单")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "Path", dataType = "long")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        boolean result = this.menuService.delete(id);
        if (result) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败，请重试");
        }
    }

    @ApiOperation(value = "更新菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "Path", dataType = "long"),
            @ApiImplicitParam(name = "menuUpdateForm", value = "菜单更新信息", required = true, dataType = "MenuUpdateForm")
    })
    @PutMapping("/{id}")
    public Result update(@PathVariable long id, @RequestBody MenuUpdateForm menuUpdateForm){
        Menu menu = menuUpdateForm.toPo(id, Menu.class);
        boolean result = this.menuService.update(menu);
        if (result) {
            return Result.success("更新成功");
        } else {
            return Result.fail("更新失败，请重试");
        }
    }

    @ApiOperation(value = "根据ID查询菜单")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "Path", dataType = "long")
    @GetMapping("/{id}")
    public Result get(@PathVariable long id){
        Menu menu = this.menuService.get(id);
        return Result.success(menu);
    }

    @ApiOperation(value = "根据所有查询菜单")
    @GetMapping("/all")
    public Result getAll(List<Long> menuIds){
        List<Menu> menus = this.menuService.getAll(menuIds);
        return Result.success(menus);
    }

    @ApiOperation(value = "条件查询菜单")
    @ApiImplicitParam(name = "menuQueryForm", value = "菜单表单信息", required = true, dataType = "MenuQueryForm")
    @PostMapping("/search")
    public Result query(@Valid @RequestBody MenuQueryForm menuQueryForm){
        MenuQueryParam menuQueryParam = menuQueryForm.toParam(MenuQueryParam.class);
        IPage<Menu> page = this.menuService.query(menuQueryForm.getPage(), menuQueryParam);
        return Result.success(page.getRecords());
    }

}
