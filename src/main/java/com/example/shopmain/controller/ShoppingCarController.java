package com.example.shopmain.controller;

import com.example.shopmain.dto.Mensaje;
import com.example.shopmain.dto.ShoppingCarDto;
import com.example.shopmain.entity.ShoppingCar;
import com.example.shopmain.security.service.UserService;
import com.example.shopmain.service.ProductXSIzeService;
import com.example.shopmain.service.ShoppingCarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ShoppingCar")
@CrossOrigin(origins = "http://localhost:4200",methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class ShoppingCarController {
    @Autowired
    ShoppingCarService shoppingCarService;
    @Autowired
    UserService userService;
    @Autowired
    ProductXSIzeService productXSIzeService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listall")
    public ResponseEntity<List<ShoppingCar>> listall(){
        List<ShoppingCar> listall =shoppingCarService.listall();
        return new ResponseEntity<>(listall, HttpStatus.OK);
    }
    @GetMapping("/list/{user}")
    public  ResponseEntity<List<ShoppingCar>> list (@PathVariable("user")String user){
        if (!userService.existsByMail(user)){
            return new ResponseEntity(new Mensaje("El usuario no existe"),HttpStatus.BAD_REQUEST);
        }
        List<ShoppingCar> list = shoppingCarService.list(userService.findByMail(user).get());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @PostMapping("/Add")
    public ResponseEntity<?> add(@RequestBody ShoppingCarDto shoppingCarDto){
        if (StringUtils.isAllBlank(shoppingCarDto.getUser()) || !userService.existsByMail(shoppingCarDto.getUser())){
            return new ResponseEntity<>(new Mensaje("error con el  usuario"),HttpStatus.BAD_REQUEST);
        }
        if (!productXSIzeService.existByid(shoppingCarDto.getProductxsize())){
            return new ResponseEntity<>(new Mensaje("el producto no existe"),HttpStatus.BAD_REQUEST);
        }
        if(shoppingCarService.existByUserAndShoppingcar(userService.findByMail(shoppingCarDto.getUser()).get(),productXSIzeService.getOne(shoppingCarDto.getProductxsize()).get())){
            return new ResponseEntity<>(new Mensaje("Ese articulo ya esta en el carrito"),HttpStatus.BAD_REQUEST);
        }
        if (productXSIzeService.getOne(shoppingCarDto.getProductxsize()).get().getTotal_amount() < shoppingCarDto.getAmount()){
            return new ResponseEntity<>(new Mensaje("Stock insuficiente"),HttpStatus.BAD_REQUEST);
        }
        ShoppingCar shoppingCar= new ShoppingCar(shoppingCarDto.getAmount(),false,userService.findByMail(shoppingCarDto.getUser()).get(),productXSIzeService.getOne(shoppingCarDto.getProductxsize()).get());
        shoppingCarService.save(shoppingCar);
        return new ResponseEntity<>(new Mensaje("producto agregado correectamente"),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable ("id")Long id,@RequestBody ShoppingCarDto shoppingCarDto){
        if(!shoppingCarService.existbyid(id)){
            return new ResponseEntity(new Mensaje("ese producto no existe"),HttpStatus.BAD_REQUEST);
        }
        if (shoppingCarDto.getAmount()> shoppingCarService.getOne(id).get().getProductXSize().getTotal_amount()){
            return new ResponseEntity(new Mensaje("Stock insuficiente"),HttpStatus.BAD_REQUEST);
        }
        ShoppingCar shoppingCar = shoppingCarService.getOne(id).get();
        shoppingCar.setAmount(shoppingCarDto.getAmount());
        shoppingCarService.save(shoppingCar);
        return new ResponseEntity(new Mensaje("producto actualizado"),HttpStatus.OK);
    }
    @DeleteMapping("/remove/{id}")
    public  ResponseEntity remove(@PathVariable ("id")Long id){
        if(!shoppingCarService.existbyid(id)){
            return new ResponseEntity(new Mensaje("el articulo no existe"),HttpStatus.BAD_REQUEST);
        }
        //TODO: verificar que el usuario solo pueda eliminar su carrito
        shoppingCarService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"),HttpStatus.OK);
    }
    @GetMapping("/count/{mail}")
     public Long count(@PathVariable("mail")String mail){
        return shoppingCarService.count(userService.findByMail(mail).get());
    }
    @GetMapping("/total/{mail}")
    public double total(@PathVariable("mail")String mail){
        return shoppingCarService.totalPrice(userService.findByMail(mail).get());
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<ShoppingCar> getone(@PathVariable ("id")Long id){
        if(!shoppingCarService.existbyid(id)){
            return new ResponseEntity(new Mensaje("El producto no existe"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(shoppingCarService.getOne(id),HttpStatus.OK);
    }
}
