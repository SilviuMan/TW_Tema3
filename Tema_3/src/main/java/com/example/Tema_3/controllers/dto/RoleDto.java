package com.example.Tema_3.controllers.dto;

import com.example.Tema_3.models.Resources;
import com.example.Tema_3.models.Rights;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class RoleDto {
    public RoleDto() {
    }
    public RoleDto(Long id) {
        this.id=id;
    }

    private Long id;

    private Set<Rights> rights = new HashSet<>();

    private Set<Resources> resources = new HashSet<>();

    public Set<Rights> getRights() {
        return rights;
    }

    public RoleDto setRights(Set<Rights> rights) {
        this.rights = rights;
        return this;
    }

    public Set<Resources> getResources() {
        return resources;
    }

    public RoleDto setResources(Set<Resources> resources) {
        this.resources = resources;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RoleDto setId(Long id) {
        this.id = id;
        return this;
    }



    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", rights=" + rights +
                ", resources=" + resources +
                '}';
    }
}
