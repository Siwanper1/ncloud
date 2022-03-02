package com.swp.organization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.organization.entity.form.*;
import com.swp.organization.entity.param.GroupQueryParam;
import com.swp.organization.entity.param.PositionQueryParam;
import com.swp.organization.entity.po.Group;
import com.swp.organization.entity.po.Position;
import com.swp.organization.service.GroupService;
import com.swp.organization.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
@Api("用户组管理")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @ApiOperation("添加用户组")
    @ApiImplicitParam(name = "positionForm", value = "添加用户组表单", required = true, dataType = "PositionForm")
    @PostMapping
    public Result add(@Valid @RequestBody GroupForm groupForm) {
        Group group = groupForm.toPo(Group.class);
        boolean result = this.groupService.add(group);
        if (result) {
            return Result.success(group);
        } else {
            return Result.fail("添加失败，请重试");
        }
    }

    @ApiOperation("删除用户组")
    @ApiImplicitParam(name = "id", value = "删除用户组ID", required = true, paramType = "Path", dataType = "Long")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        boolean result = this.groupService.delete(id);
        if (result) {
            return Result.success("删除成功");
        } else {
            return Result.fail("添加失败，请重试");
        }
    }

    @ApiOperation("修改用户组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户组ID", required = true, paramType = "Path", dataType = "Long"),
            @ApiImplicitParam(name = "groupUpdateForm", value = "更新用户组表单", required = true, dataType = "GroupUpdateForm")
    })
    @PutMapping("/{id}")
    public Result update(@PathVariable long id, @RequestBody GroupUpdateForm groupUpdateForm){
        boolean result = this.groupService.update(groupUpdateForm.toPo(id, Group.class));
        if (result) {
            return Result.success("更新成功");
        } else {
            return Result.fail("更新失败，请重试");
        }
    }

    @ApiOperation("根据ID查找用户组")
    @ApiImplicitParam(name = "id", value = "删除用户组ID", required = true, paramType = "Path", dataType = "Long")
    @GetMapping("/{id}")
    public Result get(@PathVariable long id){
        return Result.success(this.groupService.get(id));
    }

    @ApiOperation("查询所有用户组")
    @GetMapping("/all")
    public Result getAll(){
        return Result.success(this.groupService.getAll());
    }

    @ApiOperation("条件查询用户组")
    @ApiImplicitParam(name = "groupQueryForm", value = "条件查询用户组表单", required = true, dataType = "GroupQueryForm")
    @PostMapping("/search")
    public Result query(@RequestBody GroupQueryForm groupQueryForm) {
        GroupQueryParam groupQueryParam = groupQueryForm.toParam(GroupQueryParam.class);
        IPage<Group> groups = this.groupService.query(groupQueryForm.getPage(), groupQueryParam);
        return Result.success(groups.getRecords());
    }

}
