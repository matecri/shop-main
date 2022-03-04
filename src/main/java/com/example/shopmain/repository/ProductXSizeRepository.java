package com.example.shopmain.repository;

import com.example.shopmain.entity.Product;
import com.example.shopmain.entity.ProductXSize;
import com.example.shopmain.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductXSizeRepository extends JpaRepository<ProductXSize,Long> {
    boolean existsByProduct (Product product);
    List<ProductXSize> findAllByProductOrderBySizeAsc(Product product);
    boolean existsByProductAndSize(Product product, Size size);
}
