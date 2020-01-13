package com.example.Tema_3.models;

import javax.persistence.*;

@Entity
@Table(name = "resources_role", schema = "tema3", catalog = "")
@IdClass(ResourcesRolePK.class)
public class ResourcesRole {
    private long roleId;
    private long resourcesId;

    @Id
    @Column(name = "role_id", nullable = false)
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "resources_id", nullable = false)
    public long getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(long resourcesId) {
        this.resourcesId = resourcesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourcesRole that = (ResourcesRole) o;

        if (roleId != that.roleId) return false;
        if (resourcesId != that.resourcesId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (int) (resourcesId ^ (resourcesId >>> 32));
        return result;
    }
}
