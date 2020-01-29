package com.bobrayeden.beernextdoor.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBeer;
    private String nameBeer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.REFRESH)
    @JoinColumn(name = "id_type")
    private Type type;

    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.REFRESH)
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @ManyToMany(mappedBy = "storeBeers")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();

    @ManyToMany(mappedBy = "favBeers")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<User> beers = new ArrayList<>();

    public Beer() {
    }

    public Long getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(Long idBeer) {
        this.idBeer = idBeer;
    }

    public String getNameBeer() {
        return nameBeer;
    }

    public void setNameBeer(String nameBeer) {
        this.nameBeer = nameBeer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public List<User> getBeers() {
        return beers;
    }

    public void setBeers(List<User> beers) {
        this.beers = beers;
    }
}
