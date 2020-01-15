package com.example.tema3.tema3.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "resources")
public class Resources {

    @Id
    @GeneratedValue
    @Column(name = "resources_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String text;

//    @ManyToMany(
//            cascade = {CascadeType.PERSIST,
//                    CascadeType.MERGE
//            }
//    )
//    @JoinTable(name = "resource_rights",
//            joinColumns = @JoinColumn(name = "resource_id"),
//            inverseJoinColumns = @JoinColumn(name = "rights_id")
//    )

//    @OneToMany(
//            mappedBy = "resources",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<ResourcesRights> rights = new LinkedHashSet<>();

    public Resources() {
    }

    public Resources(Long id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Resources( String name, String text) {
        this.name = name;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public Set<ResourcesRights> getRights() {
//        return rights;
//    }
//
//    public void setRights(Set<ResourcesRights> rights) {
//        this.rights = rights;
//    }


    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
