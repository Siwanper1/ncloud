package com.swp.auth.authorizationserver.provider;

import com.swp.auth.authorizationserver.entity.Role;
import com.swp.auth.authorizationserver.entity.User;
import com.swp.ncloud.common.core.entity.vo.Result;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrganizationProviderFallback implements OrganizationProvider {
    @Override
    public Result<User> query(String uniqueId) {
        return Result.success(new User());
    }

    @Override
    public Result<Set<Role>> getByUserId(Long userId) {
        return Result.success(new HashSet<Role>());
    }
}
