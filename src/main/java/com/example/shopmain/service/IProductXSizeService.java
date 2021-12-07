package com.example.shopmain.service;

import com.example.shopmain.entity.ProductXSize;

import java.util.List;
import java.util.Optional;

public interface IProductXSizeService {
    public List<ProductXSize> list();
    public Optional<ProductXSize> getOne(Long id);
    public void save(ProductXSize productXSize);
}
