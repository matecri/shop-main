package com.example.shopmain.service;

import com.example.shopmain.entity.Product;
import com.example.shopmain.entity.ProductXSize;
import com.example.shopmain.entity.Size;
import com.example.shopmain.entity.Type;
import com.example.shopmain.repository.ProductXSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductXSIzeService {
    //TODO: crear productxsizeDTO y controlar lo que retorna
    @Autowired
    ProductXSizeRepository productXSizeRepository;
    public List<ProductXSize> list(){
        return productXSizeRepository.findAll();
    }
    public Optional<ProductXSize> getOne(Long id){
        return productXSizeRepository.findById(id);
    }
    public void save(ProductXSize productXSize){
        if(productXSize.getTotal_amount()>=0){
            productXSizeRepository.save(productXSize);
        }
    }
    public  boolean existByProduct(Product product){
        return productXSizeRepository.existsByProduct(product);
    }
    public  List<ProductXSize> listProductxSize(Product product){
        return productXSizeRepository.findAllByProductOrderBySizeAsc(product);
    }
    public boolean existByid(Long id){
        return productXSizeRepository.existsById(id);
    }
    public void delete(Long id){
        productXSizeRepository.deleteById(id);
    }
    public boolean existbyProductSize(Product product, Size size){
        return productXSizeRepository.existsByProductAndSize(product,size);
    }
}
