package com.example.shopmain.service;

import com.example.shopmain.entity.Size;

import java.util.List;
import java.util.Optional;

public interface ISizeService {

    public boolean existbyname(String name);
    public boolean exitid(Long id);
    public Size registersize(Size size);
    public List<Size> list();
    public Optional<Size> getOne(Long id);

}
