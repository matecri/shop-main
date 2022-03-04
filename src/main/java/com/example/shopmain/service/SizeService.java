package com.example.shopmain.service;
import com.example.shopmain.entity.Size;
import com.example.shopmain.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService{
    @Autowired
    SizeRepository sizeRepository;
    public boolean existbyname(String name){
        return sizeRepository.existsByName(name);
    }
    public Size registersize(Size size) {
        Size sizeDB=sizeRepository.findByName(size.getName()).orElse(null);
        if (sizeDB != null) {
            return sizeDB;
        }
        sizeDB = sizeRepository.save(size);
        return sizeDB;
    }
    public List<Size> list(){
        return sizeRepository.findAll();
    }
    public Optional<Size> getOne(Long id){
        return sizeRepository.findById(id);
    }
    public boolean exitid(Long id){
        return sizeRepository.existsById(id);
    }
}
