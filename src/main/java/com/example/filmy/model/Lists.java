package com.example.filmy.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "lists")
public class Lists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Rating")
    private Integer Rating;

    private enum status {
        OBEJRZANY, OGLADAM, PLANUJE;
    }

    private String listName;


//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "idUser"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "idRole"))
//
//    private Collection<User> users;
}
