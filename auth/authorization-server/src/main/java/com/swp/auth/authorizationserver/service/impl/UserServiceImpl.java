package com.swp.auth.authorizationserver.service.impl;

import com.swp.auth.authorizationserver.entity.User;
import com.swp.auth.authorizationserver.provider.OrganizationProvider;
import com.swp.auth.authorizationserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.query(uniqueId).getData();
    }
}
