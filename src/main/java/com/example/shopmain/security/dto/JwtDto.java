package com.example.shopmain.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDto {
    private String token;
    private String bearer ="Bearer";
    private String mail;
    private long iduser;
    private Collection<? extends GrantedAuthority > authorities;

    public JwtDto(String token, String bearer, String mail, Collection<? extends GrantedAuthority> authorities,long iduser) {
        this.token = token;
        this.bearer = bearer="Bearer";
        this.mail = mail;
        this.iduser = iduser;
        this.authorities = authorities;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
