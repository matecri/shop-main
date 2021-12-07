package com.example.shopmain.repository;

import com.example.shopmain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findBydescrip(String descrip);
    boolean existsBydescrip(String descrip);
    Optional<Product> findBybrand(String descrip);
    boolean existsBybrand(String descrip);
    Optional<Product> findBycolor(String descrip);
    boolean existsBycolor(String descrip);
    boolean existsByTitulo(String titulo);
}
