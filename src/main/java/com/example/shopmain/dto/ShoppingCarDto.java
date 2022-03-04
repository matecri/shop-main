package com.example.shopmain.dto;

import javax.validation.constraints.NotBlank;

public class ShoppingCarDto {
    @NotBlank
    private Long amount;
    private boolean bought;
    @NotBlank
    private String user;
    @NotBlank
    private Long productxsize;

    public ShoppingCarDto() {
    }

    public ShoppingCarDto(Long amount, boolean bought, String user, Long productxsize) {
        this.amount = amount;
        this.bought = bought;
        this.user = user;
        this.productxsize = productxsize;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getProductxsize() {
        return productxsize;
    }

    public void setProductxsize(Long productxsize) {
        this.productxsize = productxsize;
    }
}
