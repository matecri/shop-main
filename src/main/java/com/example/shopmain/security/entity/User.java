package com.example.shopmain.security.entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private long idUser;

    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    @Column(unique = true)
    private String mail;
    @NotNull
    private String pass;
    @NotNull
    private String address;
    @NotNull
    @Column(unique = true)
    private String document;
    @NotNull
    private String type_document;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_rol", joinColumns =@JoinColumn(name = "user_idUser"),
     inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles =new HashSet<>();

    public User() {
    }

    public User(String name, String lastname, String mail, String pass, String address, String document, String type_document) {
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.pass = pass;
        this.address = address;
        this.document = document;
        this.type_document = type_document;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
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

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
