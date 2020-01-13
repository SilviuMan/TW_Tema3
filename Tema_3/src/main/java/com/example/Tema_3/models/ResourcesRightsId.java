package com.example.Tema_3.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResourcesRightsId implements Serializable {

    @Column(name = "resources_id")
    private Long resourcesId;

    @Column(name = "rights_id")
    private Long rightsId;

    public ResourcesRightsId() {
    }

    public ResourcesRightsId(Long resources_id, Long right_id) {
        this.resourcesId = resources_id;
        this.rightsId = right_id;
    }

    public Long getResourceId() {
        return resourcesId;
    }

    public void setResourceId(Long resourceId) {
        this.resourcesId = resourceId;
    }

    public Long getRightsId() {
        return rightsId;
    }

    public void setRightsId(Long rightsId) {
        this.rightsId = rightsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourcesRightsId that = (ResourcesRightsId) o;
        return Objects.equals(resourcesId, that.resourcesId) &&
                Objects.equals(rightsId, that.rightsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourcesId, rightsId);
    }
}
