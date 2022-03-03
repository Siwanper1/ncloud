package com.swp.auth.authorizationserver.provider;

import com.swp.auth.authorizationserver.entity.Role;
import com.swp.auth.authorizationserver.entity.User;
import com.swp.ncloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "organization", fallback = OrganizationProviderFallback.class)
public interface OrganizationProvider {

    @GetMapping("/user")
    Result<User> query(@RequestParam("uniqueId") String uniqueId);

    @GetMapping("/role/user/{userId}")
    Result<Set<Role>> getByUserId(@PathVariable("userId") Long userId);

}
