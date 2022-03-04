package com.example.shopmain.dto;

public class BuyDto {
    private String address;
    private String city;
    private String cpostal;
    private String phonenumber;
    private String user;

    public BuyDto() {
    }

    public BuyDto(String address, String city, String cpostal, String phonenumber, String user) {
        this.address = address;
        this.city = city;
        this.cpostal = cpostal;
        this.phonenumber = phonenumber;
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCpostal() {
        return cpostal;
    }

    public void setCpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
