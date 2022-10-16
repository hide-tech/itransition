package com.yazukov.proj.security;

import com.yazukov.proj.domain.User;
import com.yazukov.proj.domain.help.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails(User projectUser) {
        setId(projectUser.getId());
        setEmail(projectUser.getEmail());
        setPassword(projectUser.getPassword());
        setLanguage(projectUser.getLanguage());
        setRole(projectUser.getRole());
        setSkin(projectUser.getSkin());
        setStatus(projectUser.getStatus());
        setProvider(projectUser.getProvider());
        setCollections(projectUser.getCollections());
        setLikes(projectUser.getLikes());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getStatus().equals(Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getStatus().equals(Status.ACTIVE);
    }
}
