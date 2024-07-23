package com.auth.user.security.services;

import com.auth.user.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.auth.user.models.User;
import java.util.Collection;
import java.util.List;

@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomUserDetails implements UserDetails {

    private String password;

    public CustomUserDetails() {
    }

    private String username;
    private List<CustomGranted> authorites;
    public CustomUserDetails(User user){
        this.password=user.getHashedPassword();
        this.username=user.getEmail();
        for(Role role:user.getRoles()){
            authorites.add(new CustomGranted(role));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorites;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
