package com.swp.organization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.organization.entity.form.RoleForm;
import com.swp.organization.entity.form.RoleQueryForm;
import com.swp.organization.entity.form.RoleUpdateForm;
import com.swp.organization.entity.param.RoleQueryParam;
import com.swp.organization.entity.po.Role;
import com.swp.organization.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("role")
@Api(value = "角色管理")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "添加角色")
    @ApiImplicitParam(name = "roleForm", value = "角色表单信息", required = true, dataType = "RoleForm")
    @PostMapping
    public Result add(@RequestBody @Valid RoleForm roleForm) {
        Role role = roleForm.toPo(Role.class);
        boolean result = this.roleService.add(role);
        if (result) {
            return Result.success("添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "Path", dataType = "Interger")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id){
        boolean result = this.roleService.delete(id);
        if (result) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @ApiOperation(value = "修改角色")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "Path", dataType = "Interger"),
                    @ApiImplicitParam(name = "roleUpdateForm", value = "角色表单信息", required = true, dataType = "RoleUpdateForm")
    })
    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid RoleUpdateForm roleUpdateForm) {
        Role role = roleUpdateForm.toPo(Role.class);
        role.setId(id);
        boolean result = this.roleService.update(role);
        if (result) {
            return Result.success("修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @ApiOperation(value = "获取角色", notes = "根据id获取角色")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "Path", dataType = "Interger")
    @GetMapping("/{id}")
    public Result<Role> get(@PathVariable int id) {
        log.info("get role by id : {}" ,id);
        return Result.success(this.roleService.get(id));
    }

    @ApiOperation(value = "查询所有角色")
    @GetMapping("/all")
    public Result get(){
        return Result.success(this.roleService.getAll());
    }

    @ApiOperation(value = "搜索角色")
    @ApiImplicitParam(name = "roleQueryForm", value = "搜索角色信息", required = true, dataType = "RoleQueryForm")
    @PostMapping("/search")
    public Result search(@Valid @RequestBody RoleQueryForm roleQueryForm){
        IPage page = this.roleService.query(roleQueryForm.getPage(), roleQueryForm.toParam(RoleQueryParam.class));
        return Result.success(page.getRecords());
    }

}
