package com.example.shopmain.dto;


public class UserDto {
    private String name;
    private String lastname;
    private String mail;
    private String pass;
    private String address;
    private String document;
    private String type_document;
    private Long roll;

    public UserDto() {
    }

    public UserDto(String name, String lastname, String mail, String pass, String address, String document, String type_document, Long roll) {
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.pass = pass;
        this.address = address;
        this.document = document;
        this.type_document = type_document;
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getType_document() {
        return type_document;
    }

    public void setType_document(String type_document) {
        this.type_document = type_document;
    }

    public Long getRoll() {
        return roll;
    }

    public void setRoll(Long roll) {
        this.roll = roll;
    }
}
