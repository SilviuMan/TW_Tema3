package com.example.Tema_3.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rights")
public class Rights {

    @Id
    @GeneratedValue
    @Column(name = "rights_id")
    private Long id;

    @Column
    private String name;


//    private Set<ResourcesRights> rights = new HashSet<>();

    public Rights() {
    }
    public Rights(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Rights( String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rights{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//    public void addResources(Resources resources){
//        ResourcesRights resourcesRights = new ResourcesRights(resources,this);
//        rights.add(resourcesRights);
//        resources.getRights().add(resourcesRights);
//    }
}