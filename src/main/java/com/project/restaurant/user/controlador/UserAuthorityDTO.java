package com.project.restaurant.user.controlador;

import com.project.restaurant.user.modelo.Authority;

import java.util.Set;

public class UserAuthorityDTO {
    private Set<Authority> authorities;

    // Getters y Setters
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
