package com.example.shopmain.criteria;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.StringFilter;

public class ProductCriteria {
    private StringFilter brand;
    private StringFilter color;
    private StringFilter decrip;
    private BooleanFilter show;
    private DoubleFilter price;

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

    public StringFilter getDecrip() {
        return decrip;
    }

    public void setDecrip(StringFilter decrip) {
        this.decrip = decrip;
    }

    public BooleanFilter getShow() {
        return show;
    }

    public void setShow(BooleanFilter show) {
        this.show = show;
    }

    public DoubleFilter getPrice() {
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }
}
