package com.example.shopmain.service;

import com.example.shopmain.entity.Type;

import java.util.List;
import java.util.Optional;


public interface ITypeService {
    public List<Type> list();
    public boolean existbyname(String name);
    public Type getbyname(String name);
    public Type registertype(Type type);
    public boolean existbyid(Long id);
    public Optional<Type> getOne(Long id);
}
