package com.example.Tema_3.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "resources_rights")
public class ResourcesRights {

    @EmbeddedId
    ResourcesRightsId id;

    @ManyToOne
    @MapsId("resourcesId")
    private Resources resources;

    @ManyToOne
    @MapsId("rightsId")
    private Rights rights;

    @ManyToMany(
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
//    @JoinTable(name = "resource_rights_roles",
//            joinColumns = @JoinColumn(name = "resources_rights_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
    @JoinColumns({
            @JoinColumn(name = "resources_rights_id", insertable = true, updatable = true),
            @JoinColumn(name = "role_id",insertable = true,updatable = true)
    })
    private Set<Role> role = new LinkedHashSet<>();

    public ResourcesRights() {
    }
    public ResourcesRights(Role resources) {
        this.role.add(resources) ;

    }
    public ResourcesRights(Resources resources, Rights rights) {
        this.resources = resources;
        this.rights = rights;
        this.id = new ResourcesRightsId(resources.getId(),rights.getId());
    }

    public ResourcesRightsId getId() {
        return id;
    }

    public void setId(ResourcesRightsId id) {
        this.id = id;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public Set<Role> getRoles() {
        return role;
    }

    public void setRoles(Set<Role> roles) {
        this.role = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourcesRights that = (ResourcesRights) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(resources, that.resources) &&
                Objects.equals(rights, that.rights) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resources, rights, role);
    }

    @Override
    public String toString() {
        return "ResourcesRights{" +
                "id=" + id +
                ", resources=" + resources +
                ", rights=" + rights +
                ", roles=" + role +
                '}';
    }
    public void addRole(Role role){
        ResourcesRights resourcesRights = new ResourcesRights(role);
        this.role.add(role);
        resourcesRights.getRoles().add(role);
    }
}