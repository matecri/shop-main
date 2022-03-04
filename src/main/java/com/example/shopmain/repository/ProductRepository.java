package com.example.shopmain.repository;

import com.example.shopmain.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,
        JpaSpecificationExecutor<Product> {
    boolean existsBydescrip(String descrip);
    boolean existsBybrand(String descrip);
    boolean existsBycolor(String descrip);
    List<Product> findTop10ByOrderByPviewsDesc();
    List<Product> findTop10ByMostrarTrueOrderByPviewsDesc();
}
