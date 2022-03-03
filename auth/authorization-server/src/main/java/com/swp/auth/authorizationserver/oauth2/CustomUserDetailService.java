package com.swp.auth.authorizationserver.oauth2;

import com.swp.auth.authorizationserver.entity.Role;
import com.swp.auth.authorizationserver.entity.User;
import com.swp.auth.authorizationserver.service.RoleService;
import com.swp.auth.authorizationserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        User user = userService.getByUniqueId(uniqueId);
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
        return roles.stream().map(role -> new SimpleGrantedAuthority(String.valueOf(role.getId()))).collect(Collectors.toSet());
    }
}
