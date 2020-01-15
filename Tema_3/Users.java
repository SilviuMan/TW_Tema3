package com.example.tema3.tema3.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

   @Id
   @GeneratedValue
   @Column(name = "user_id")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "roles_id")} )
    private Set<Roles> roles = new HashSet<>();

    public Users() {
    }

//    public Users(Long id, String username, String password) {
//        this.id=id;
//        this.username = username;
//        this.password = password;
//    }

    public Users( String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Users users = (Users) o;
//        return Objects.equals(id, users.id) &&
//                Objects.equals(username, users.username) &&
//                Objects.equals(password, users.password);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, username, password);
//    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
