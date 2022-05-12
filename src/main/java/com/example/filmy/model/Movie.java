package com.example.filmy.model;

import javax.persistence.*;

@Deprecated
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer Rating;

    private enum status {
        OBEJRZANY, OGLADAM, PLANUJE;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="idList", nullable=false)
    private Lists list;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }

    public Lists getList() {
        return list;
    }

    public void setList(Lists list) {
        this.list = list;
    }

    public Movie() {

    }

    public Movie(Long id, Integer rating, Lists list) {
        this.id = id;
        Rating = rating;
        this.list = list;
    }
}
