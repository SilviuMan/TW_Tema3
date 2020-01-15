package com.example.Tema_3.controllers.dto;

import com.example.Tema_3.models.Role;
import com.example.Tema_3.models.Roles;
import com.example.Tema_3.models.Users;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UsersDto {

    private Long id;

    private String username;

    private String password;

    private Set<Role> role = new HashSet<>();

    private Collection<Roles> roles;

    public UsersDto() {
    }

    public UsersDto(Long id, String username, String password) {
        this.id=id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Roles> roles) {
        this.roles = roles;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRoles(Set<Role> roles) {
        this.role = roles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDto users = (UsersDto) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(username, users.username) &&
                Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + role +
                '}';
    }
}
