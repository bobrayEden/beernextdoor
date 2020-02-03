package com.bobrayeden.beernextdoor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrand;
    private String nameBrand;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Beer> beers;

    public Brand() {
    }

    public Long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Long idBrand) {
        this.idBrand = idBrand;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }
}
