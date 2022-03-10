package com.swp.organization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.organization.entity.form.UserForm;
import com.swp.organization.entity.form.UserQueryForm;
import com.swp.organization.entity.form.UserUpdateForm;
import com.swp.organization.entity.param.UserQueryParam;
import com.swp.organization.entity.po.User;
import com.swp.organization.entity.vo.UserVo;
import com.swp.organization.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "用户管理")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户", notes = "根据用户id获取用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "string")
    @GetMapping("/{id}")
    public Result<UserVo> get(@PathVariable String id){
        log.info("get with id : {}", id);
        UserVo userVo = userService.get(id);
        return Result.success(userVo);
    }

    @ApiOperation(value = "获取用户", notes = "根据用户唯一标识（username/mobile）获取用户")
    @ApiImplicitParam(name = "uniqueId", value = "用户唯一标识(username/mobile)", required = true, paramType = "query", dataType = "string")
    @GetMapping
    public Result<User> query(@RequestParam  String uniqueId){
        log.info("query with uniqueId : {}" ,uniqueId);
        return Result.success(userService.getByUniqueId(uniqueId));
    }

    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "userForm", value = "用户表单信息", required = true, dataType = "UserForm")
    @PostMapping
    public Result add(@Valid @RequestBody UserForm userForm) {
        User user = userForm.toPo(User.class);
        boolean result = userService.add(user);
        log.info("add user : {}", result);
        if (result) {
            return Result.success(new UserVo(user));
        } else {
            return Result.fail("添加失败");
        }
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "string")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        boolean result = userService.delete(id);
        log.info("delete result : {}", result);
        if (result) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "updateForm", value = "更新信息", required = true, dataType = "UserUpdateForm")
    })
    @PutMapping("/{id}")
    public Result update(@PathVariable long id, @Valid @RequestBody UserUpdateForm updateForm){
        User user = updateForm.toPo(User.class);
        user.setId(id);
        boolean result = userService.update(user);
        if (result) {
            return Result.success("修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @ApiOperation(value = "搜索用户")
    @ApiImplicitParam(name = "queryForm", value = "搜索用户信息", required = true, dataType = "UserQueryForm")
    @PostMapping("/search")
    public Result search(@RequestBody UserQueryForm queryForm) {
        UserQueryParam queryParam = queryForm.toParam(UserQueryParam.class);
        IPage users = userService.query(queryForm.getPage(), queryParam);
        return Result.success(users);
    }

}
