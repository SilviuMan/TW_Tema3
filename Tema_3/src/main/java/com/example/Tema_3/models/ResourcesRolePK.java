package com.example.Tema_3.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ResourcesRolePK implements Serializable {
    private long roleId;
    private long resourcesId;

    @Column(name = "role_id", nullable = false)
    @Id
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "resources_id", nullable = false)
    @Id
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

        ResourcesRolePK that = (ResourcesRolePK) o;

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
