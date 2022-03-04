package com.example.shopmain.service;

import com.example.shopmain.dto.Mensaje;
import com.example.shopmain.entity.Product;
import com.example.shopmain.entity.ProductXSize;
import com.example.shopmain.entity.ShoppingCar;
import com.example.shopmain.entity.Size;
import com.example.shopmain.repository.ShoppingCarRepository;
import com.example.shopmain.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class ShoppingCarService {
    @Autowired
    ShoppingCarRepository shoppingCarRepository;
    public List<ShoppingCar> listall(){
        return shoppingCarRepository.findAll();
    }
    public List<ShoppingCar> list (User user){
        return shoppingCarRepository.findAllByUserAndBoughtFalse(user);
    }
    public double totalPrice(User user){
        double total= Long.valueOf(0);
        List<ShoppingCar> list = shoppingCarRepository.findAllByUserAndBoughtFalse(user);
        for (int i=0;i<list.size();i++){
            total=total+(list.get(i).getAmount()*list.get(i).getProductXSize().getProduct().getPrice());
        }
        return total;
    }
    public void save (ShoppingCar shoppingCar){
        shoppingCarRepository.save(shoppingCar);
    }
    public void delete(Long id){
        shoppingCarRepository.deleteById(id);
    }
    public boolean existByUser(User user){
        return shoppingCarRepository.existsByUser(user);
    }
    public boolean existbyid(Long id){
        return shoppingCarRepository.existsByIdshopping(id);
    }
    public Optional<ShoppingCar> getOne (Long id){
        return  shoppingCarRepository.findById(id);
    }
    public boolean existByUserAndShoppingcar(User user, ProductXSize id){
        return shoppingCarRepository.existsByUserAndProductXSizeAndBoughtFalse(user,id);
    }
    public void deleteallbyuser(User user){
        shoppingCarRepository.deleteAllByUser(user);
    }
    public Long count(User user){
        return shoppingCarRepository.countAllByUserAndBoughtFalse(user);
    }
}
