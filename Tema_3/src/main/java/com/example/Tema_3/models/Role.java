package com.example.Tema_3.models;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "role")
public class Role {

    public Role() {
    }
    public Role(Long id) {
        this.id=id;
    }

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rights_roles",joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "rights_id")} )
    private Set<Rights> rights = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "resources_role",joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "resources_id")} )
    private Set<Resources> resources = new HashSet<>();

    public Set<Rights> getRights() {
        return rights;
    }

    public void setRights(Set<Rights> rights) {
        this.rights = rights;
    }

    public Set<Resources> getResources() {
        return resources;
    }

    public void setResources(Set<Resources> resources) {
        this.resources = resources;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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