package com.bobrayeden.beernextdoor.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nameUser;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(name = "user_fav_type",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "type_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Type> favTypes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_fav_beer",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "beer_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Beer> favBeers = new ArrayList<>();

    public User() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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

    public List<Type> getFavTypes() {
        return favTypes;
    }

    public void setFavTypes(List<Type> favTypes) {
        this.favTypes = favTypes;
    }

    public List<Beer> getFavBeers() {
        return favBeers;
    }

    public void setFavBeers(List<Beer> favBeers) {
        this.favBeers = favBeers;
    }
}
