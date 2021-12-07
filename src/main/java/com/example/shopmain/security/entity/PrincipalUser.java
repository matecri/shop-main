package com.example.shopmain.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalUser implements UserDetails {

    private String name;
    private String lastname;
    private String mail;
    private String pass;
    private String address;
    private String document;
    private String type_document;
    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(String name, String lastname, String mail, String pass, String address, String document, String type_document, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.pass = pass;
        this.address = address;
        this.document = document;
        this.type_document = type_document;
        this.authorities = authorities;
    }
    public static PrincipalUser build(User user){
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getRolname().name())).collect(Collectors.toList());
        return new PrincipalUser(user.getName(), user.getLastname(), user.getMail(), user.getPass(), user.getAddress(), user.getDocument(), user.getType_document(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getDocument() {
        return document;
    }

    public String getType_document() {
        return type_document;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }
}
