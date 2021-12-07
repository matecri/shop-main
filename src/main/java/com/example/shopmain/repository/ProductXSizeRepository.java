package com.example.shopmain.repository;

import com.example.shopmain.entity.ProductXSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductXSizeRepository extends JpaRepository<ProductXSize,Long> {

}
