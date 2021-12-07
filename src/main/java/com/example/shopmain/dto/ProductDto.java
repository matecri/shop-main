package com.example.shopmain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ProductDto {


    private String img;
    private String titulo;
    private String descrip;
    private String brand;
    private String color;
    private Boolean mostrar;
    private Double price;
    private Long type;

    public ProductDto() {
    }

    public ProductDto(String img, String titulo, String descrip, String brand, String color, Boolean mostrar, Double price, Long type) {
        this.img = img;
        this.titulo = titulo;
        this.descrip = descrip;
        this.brand = brand;
        this.color = color;
        this.mostrar = mostrar;
        this.price = price;
        this.type = type;
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

    public Boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(Boolean mostrar) {
        this.mostrar = mostrar;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
