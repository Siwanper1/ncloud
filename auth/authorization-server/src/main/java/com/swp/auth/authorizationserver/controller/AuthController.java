package com.swp.auth.authorizationserver.controller;

import com.swp.auth.authorizationserver.entity.Role;
import com.swp.auth.authorizationserver.entity.User;
import com.swp.auth.authorizationserver.service.RoleService;
import com.swp.auth.authorizationserver.service.UserService;
import com.swp.ncloud.common.core.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/user")
    public Result getByUniqueId(@RequestParam("uniqueId") String uniqueId){
        User user = this.userService.getByUniqueId(uniqueId);
        return Result.success(user);
    }

    @GetMapping("/role/user/{userId}")
    public Result queryRolesByUserId(@PathVariable String userId){
        Set<Role> roles = this.roleService.queryRolesByUserId(userId);
        return Result.success(roles);
    }
}
