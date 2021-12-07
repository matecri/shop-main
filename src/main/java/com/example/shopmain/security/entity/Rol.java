package com.example.shopmain.security.entity;


import com.example.shopmain.security.enums.RolName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolname;

    public Rol() {
    }

    public Rol(RolName rolname) {
        this.rolname = rolname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolName getRolname() {
        return rolname;
    }

    public void setRolname(RolName rolname) {
        this.rolname = rolname;
    }
}
