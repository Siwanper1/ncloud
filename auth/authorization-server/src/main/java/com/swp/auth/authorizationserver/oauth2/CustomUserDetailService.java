package com.swp.auth.authorizationserver.oauth2;

import com.swp.auth.authorizationserver.entity.Role;
import com.swp.auth.authorizationserver.entity.User;
import com.swp.auth.authorizationserver.service.RoleService;
import com.swp.auth.authorizationserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        User user = userService.getByUniqueId(uniqueId);
        log.info("user : {}" , user);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                getGrantedAuthority(user)
        );
    }

    private Set<GrantedAuthority> getGrantedAuthority(User user){
        Set<Role> roles = roleService.queryRolesByUserId(String.valueOf(user.getId()));
        log.info("roles : {}", roles);
        return roles.stream().map(role -> new SimpleGrantedAuthority(String.valueOf(role.getCode()))).collect(Collectors.toSet());
    }
}
