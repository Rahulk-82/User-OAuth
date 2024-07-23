package com.auth.user.security.services;

import com.auth.user.models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGranted implements GrantedAuthority {
    public String role;
    public CustomGranted(Role role){
        this.role=role.getName();
    }

    public CustomGranted() {
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
