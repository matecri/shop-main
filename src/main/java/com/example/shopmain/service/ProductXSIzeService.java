package com.example.shopmain.service;

import com.example.shopmain.entity.ProductXSize;
import com.example.shopmain.entity.Type;
import com.example.shopmain.repository.ProductXSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductXSIzeService implements IProductXSizeService{
    @Autowired
    ProductXSizeRepository productXSizeRepository;
    public List<ProductXSize> list(){
        return productXSizeRepository.findAll();
    }
    public Optional<ProductXSize> getOne(Long id){
        return productXSizeRepository.findById(id);
    }
    public void save(ProductXSize productXSize){
        productXSizeRepository.save(productXSize);
    }
}
