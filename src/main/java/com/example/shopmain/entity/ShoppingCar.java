package com.example.shopmain.entity;

import com.example.shopmain.security.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "shopping_car")
public class ShoppingCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idshopping")
    private Long idshopping;
    private Long amount;
    private boolean bought;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_idUser")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private ProductXSize productXSize;

    public ShoppingCar() {
    }

    public ShoppingCar(Long amount, boolean bought, User user, ProductXSize productXSize) {
        this.amount = amount;
        this.bought = bought;
        this.user = user;
        this.productXSize = productXSize;
    }

    public Long getIdshopping() {
        return idshopping;
    }

    public void setIdshopping(Long idshopping) {
        this.idshopping = idshopping;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductXSize getProductXSize() {
        return productXSize;
    }

    public void setProductXSize(ProductXSize productXSize) {
        this.productXSize = productXSize;
    }
}
