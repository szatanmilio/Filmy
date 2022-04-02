package com.example.filmy.model;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "username")
    private String username;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "idRole"))

    private Collection<Role> roles;

    public User() {

    }

    public User(String firstName, String email, String password, Collection<Role> roles) {
        super();
        this.username = firstName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public Long getId() {
        return idUser;
    }
    public void setId(Long id) {
        this.idUser = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String firstName) {
        this.username = firstName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

}
