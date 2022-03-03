package com.swp.auth.authorizationserver.service;

import com.swp.auth.authorizationserver.entity.User;

public interface UserService {

    User getByUniqueId(String uniqueId);

}
