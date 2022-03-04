package com.example.shopmain.entity;

import com.example.shopmain.security.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "Buy")
public class Buy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private String Cpostal;
    private String phonenumber;
    @Column(length = 255, nullable = false)
    private String descript;
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_idUser")
    private User user;
    public Buy() {
    }

    public Buy(String address, String city, String cpostal, String phonenumber, String descript, LocalDateTime fecha, User user) {
        this.address = address;
        this.city = city;
        Cpostal = cpostal;
        this.phonenumber = phonenumber;
        this.descript = descript;
        this.fecha = fecha;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Cpostal;
    }

    public void setCpostal(String cpostal) {
        Cpostal = cpostal;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
