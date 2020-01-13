package com.example.Tema_3.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RightsRolesPK implements Serializable {
    private long roleId;
    private long rightsId;

    @Column(name = "role_id", nullable = false)
    @Id
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "rights_id", nullable = false)
    @Id
    public long getRightsId() {
        return rightsId;
    }

    public void setRightsId(long rightsId) {
        this.rightsId = rightsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RightsRolesPK that = (RightsRolesPK) o;

        if (roleId != that.roleId) return false;
        if (rightsId != that.rightsId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (int) (rightsId ^ (rightsId >>> 32));
        return result;
    }
}
