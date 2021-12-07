package com.example.shopmain.service;

import com.example.shopmain.criteria.ProductCriteria;

import com.example.shopmain.entity.Product;
import com.example.shopmain.repository.ProductRepository;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService extends QueryService implements IProductService  {
    @Autowired
    ProductRepository productRepository;
   public  List<Product> findByCriteria(ProductCriteria productCriteria){
       final Specification<Product> specification =createSpecification((productCriteria));
       List<Product> product= productRepository.findAll(specification);
       return product;
   }
    public Optional<Product> getOne(Long id){
        return productRepository.findById(id);
    }
    public Product getBydescrip(String descrip){
        return productRepository.findBydescrip(descrip).orElse(null);
    }
    public void save(Product product){
        productRepository.save(product);
    }
    public boolean exitid(Long id){
        return productRepository.existsById(id);
    }
    public boolean existsBydescrip(String descrip){
        return productRepository.existsBydescrip(descrip);
    }
    public void registerProduct(Product product){
        productRepository.save(product);
    }
    public Product getBybrand(String brand){
        return productRepository.findBybrand(brand).orElse(null);
    }
    public boolean existsBybrand(String brand){
        return productRepository.existsBybrand(brand);
    }
    public Product getBycolor(String color){
        return productRepository.findBycolor(color).orElse(null);
    }
    public boolean existsBycolor(String color){
        return productRepository.existsBycolor(color);
    }
    public boolean existsByTitulo(String titulo){
        return productRepository.existsByTitulo(titulo);
    }
    public void delete(Long id){
        productRepository.deleteById(id);
    }
    private Specification<Product> createSpecification(ProductCriteria criteria){
        Specification<Product> specification =Specification.where(null);
        if(criteria !=null){
            /*if(criteria.getColor() != null){
                specification =
                        specification.and(buildStringSpecification(criteria.getColor(),Product_));
            }*/
        }
        return specification;
    }
}
