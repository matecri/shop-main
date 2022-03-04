package com.example.shopmain.repository;

import com.example.shopmain.entity.ProductXSize;
import com.example.shopmain.entity.ShoppingCar;
import com.example.shopmain.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCarRepository extends JpaRepository<ShoppingCar,Long> {
    List<ShoppingCar> findAllByUserAndBoughtFalse(User user);
    boolean existsByUser(User user);
    boolean existsByIdshopping (Long id);
    boolean existsByUserAndProductXSizeAndBoughtFalse(User user, ProductXSize id);
    void deleteAllByUser (User user);
    void deleteByIdshopping(Long id);
    Long countAllByUserAndBoughtFalse(User user);
}
