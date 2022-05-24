//package com.example.filmy.model;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.Set;
//@Deprecated
//@Entity
//@Table(name = "lists")
//public class Lists {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idList;
//
//    private String listName;
//
//
//    @OneToMany
//    private Set<Movie> movies;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name="idUser", nullable=false)
//    private User user;
//
//    public Long getIdList() {
//        return idList;
//    }
//
//    public void setIdList(Long idList) {
//        this.idList = idList;
//    }
//
//    public String getListName() {
//        return listName;
//    }
//
//    public void setListName(String listName) {
//        this.listName = listName;
//    }
//
//    public Set<Movie> getMovies() {
//        return movies;
//    }
//
//    public void setMovies(Set<Movie> movies) {
//        this.movies = movies;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Lists() {
//
//    }
//
//    public Lists(Long idList, String listName, Set<Movie> movies) {
//        this.idList = idList;
//        this.listName = listName;
//        this.movies = movies;
//    }
//
//}
