package com.example.Tema_3.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ResourcesRightsRolePK implements Serializable {
    private long resourcesRightsResourcesId;
    private long resourcesRightsRightsId;
    private long roleId;

    @Column(name = "resources_rights_resources_id", nullable = false)
    @Id
    public long getResourcesRightsResourcesId() {
        return resourcesRightsResourcesId;
    }

    public void setResourcesRightsResourcesId(long resourcesRightsResourcesId) {
        this.resourcesRightsResourcesId = resourcesRightsResourcesId;
    }

    @Column(name = "resources_rights_rights_id", nullable = false)
    @Id
    public long getResourcesRightsRightsId() {
        return resourcesRightsRightsId;
    }

    public void setResourcesRightsRightsId(long resourcesRightsRightsId) {
        this.resourcesRightsRightsId = resourcesRightsRightsId;
    }

    @Column(name = "role_id", nullable = false)
    @Id
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourcesRightsRolePK that = (ResourcesRightsRolePK) o;

        if (resourcesRightsResourcesId != that.resourcesRightsResourcesId) return false;
        if (resourcesRightsRightsId != that.resourcesRightsRightsId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (resourcesRightsResourcesId ^ (resourcesRightsResourcesId >>> 32));
        result = 31 * result + (int) (resourcesRightsRightsId ^ (resourcesRightsRightsId >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        return result;
    }
}
