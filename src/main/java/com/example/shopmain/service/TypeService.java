package com.example.shopmain.service;

import com.example.shopmain.entity.Type;
import com.example.shopmain.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TypeService {
    @Autowired
    TypeRepository typeRepository;
    public List<Type> list(){
        return typeRepository.findAll();
    }
    public boolean existbyname(String name){
        return typeRepository.existsByName(name);
    }
    public Type getbyname(String name){
        return  typeRepository.findByName(name).orElse(null);
    }
    public Type registertype(Type type) {
        Type typeDB = typeRepository.findByName(type.getName()).orElse(null);
        if (typeDB != null) {
            return typeDB;
        }
        typeDB = typeRepository.save(type);
        return typeDB;
    }
    public boolean existbyid(Long id){
        return typeRepository.existsById(id);
    }
    public Optional<Type> getOne(Long id){
        return typeRepository.findById(id);
    }
}
