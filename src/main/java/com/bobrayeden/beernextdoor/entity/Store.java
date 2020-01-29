package com.bobrayeden.beernextdoor.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStore;
    private String nameStore;
    private String address;
    private Boolean isTemple;
    private String picturePath;

    @ManyToMany
    @JoinTable(name = "store_beers",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "beer_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Beer> storeBeers = new ArrayList<>();

    public Store() {
    }

    public Long getIdStore() {
        return idStore;
    }

    public void setIdStore(Long idStore) {
        this.idStore = idStore;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getTemple() {
        return isTemple;
    }

    public void setTemple(Boolean temple) {
        isTemple = temple;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public List<Beer> getStoreBeers() {
        return storeBeers;
    }

    public void setStoreBeers(List<Beer> storeBeers) {
        this.storeBeers = storeBeers;
    }
}
