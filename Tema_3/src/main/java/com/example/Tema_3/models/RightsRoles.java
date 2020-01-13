package com.example.Tema_3.models;

import javax.persistence.*;

@Entity
@Table(name = "rights_roles", schema = "tema3", catalog = "")
@IdClass(RightsRolesPK.class)
public class RightsRoles {
    private long roleId;
    private long rightsId;

    @Id
    @Column(name = "role_id", nullable = false)
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "rights_id", nullable = false)
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

        RightsRoles that = (RightsRoles) o;

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
