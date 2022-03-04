package com.example.shopmain.criteria;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.security.PrivateKey;

public class ProductCriteria {
    private StringFilter type;
    private StringFilter titulo;
    private StringFilter brand;
    private StringFilter color;
    private BooleanFilter mostar;

    public BooleanFilter getMostar() {
        return mostar;
    }

    public void setMostar(BooleanFilter mostar) {
        this.mostar = mostar;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getTitulo() {
        return titulo;
    }

    public void setTitulo(StringFilter titulo) {
        this.titulo = titulo;
    }

    public StringFilter getBrand() {
        return brand;
    }

    public void setBrand(StringFilter brand) {
        this.brand = brand;
    }

    public StringFilter getColor() {
        return color;
    }

    public void setColor(StringFilter color) {
        this.color = color;
    }
}
