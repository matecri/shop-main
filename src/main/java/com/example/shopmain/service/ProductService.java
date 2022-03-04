package com.example.shopmain.service;

import com.example.shopmain.criteria.ProductCriteria;

import com.example.shopmain.entity.Product;
import com.example.shopmain.entity.Product_;
import com.example.shopmain.entity.Type_;
import com.example.shopmain.repository.ProductRepository;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class ProductService extends QueryService<Product>{
    @Autowired
    ProductRepository productRepository;
   public  List<Product> findByCriteriaUser(ProductCriteria productCriteria){
       final Specification<Product> specification = createSpecification(productCriteria);
       List<Product> products = productRepository.findAll(specification);
       return products;
   }
    private Specification<Product> createSpecification(ProductCriteria criteria){
        Specification<Product> specification = Specification.where(null);
        if(criteria != null){
            if(criteria.getBrand() != null) {
                specification =
                        specification.and(buildStringSpecification(criteria.getBrand(), Product_.brand));
            }
            if(criteria.getColor() != null) {
                specification =
                        specification.and(buildStringSpecification(criteria.getColor(), Product_.color));
            }
            if(criteria.getTitulo() != null) {
                specification =
                        specification.
                                and(buildStringSpecification(criteria.getTitulo(), Product_.titulo));
            }
            if(criteria.getType() != null) {
                specification =
                        specification.and(buildReferringEntitySpecification(criteria.getType(), Product_.type, Type_.name));
            }
            if (criteria.getMostar() != null){
                specification=
                        specification.and(buildSpecification(criteria.getMostar(),Product_.mostrar));
            }
        }
        return specification;
    }
    public Optional<Product> getOne(Long id){
        return productRepository.findById(id);
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
    public boolean existsBybrand(String brand){
        return productRepository.existsBybrand(brand);
    }
    public boolean existsBycolor(String color){
        return productRepository.existsBycolor(color);
    }
    public void delete(Long id){
        productRepository.deleteById(id);
    }
    public List<Product> popular(){
       return productRepository.findTop10ByMostrarTrueOrderByPviewsDesc();
    }
}
