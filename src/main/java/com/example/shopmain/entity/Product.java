package com.example.shopmain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    private Long idProduct;
    @Column(length = 255, nullable = false)
    private String img;
    @Column(nullable = false)
    private String titulo;
    @Column(length = 255, nullable = false)
    private String descrip;
    @Column(length = 25, nullable = false)
    private String brand;
    @Column(length = 10, nullable = false)
    private String color;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private boolean mostrar;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_idtype")
    private Type type;
    private Long pviews;

    public Product() {
    }

    public Product(String img, String titulo, String descrip, String brand, String color, Double price, boolean mostrar, Type type) {
        this.img = img;
        this.titulo = titulo;
        this.descrip = descrip;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.mostrar = mostrar;
        this.type = type;
    }

    public Long getPviews() {
        return pviews;
    }

    public void setPviews(Long pviews) {
        this.pviews = pviews;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

