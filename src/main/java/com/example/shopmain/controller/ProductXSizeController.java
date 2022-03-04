package com.example.shopmain.controller;


import com.example.shopmain.dto.Mensaje;
import com.example.shopmain.dto.ProductXSizeDto;
import com.example.shopmain.entity.Product;
import com.example.shopmain.entity.ProductXSize;
import com.example.shopmain.service.ProductService;
import com.example.shopmain.service.ProductXSIzeService;
import com.example.shopmain.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ProductXSIze")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class ProductXSizeController {
    @Autowired
    ProductXSIzeService productXSIzeService;
    @Autowired
    ProductService productService;
    @Autowired
    SizeService sizeService;
@GetMapping("/list/{id}")
    public ResponseEntity<?> getbyProduct(@PathVariable("id")Long id) {
    if (!productService.exitid(id)){
        return new ResponseEntity(new Mensaje("ese producto no existe"),HttpStatus.BAD_REQUEST);
    }
    Product product = productService.getOne(id).get();
    if(!productXSIzeService.existByProduct(product)){
        return new ResponseEntity(new Mensaje("error producto no tiene tallas registradas"), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<List<ProductXSize>> (productXSIzeService.listProductxSize(product),HttpStatus.OK);
}
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductXSizeDto productXSizeDto){
        if (productXSizeDto.getTotal_amount()<0){
            return new ResponseEntity(new Mensaje("cantidad invalida"),HttpStatus.BAD_REQUEST);
        }
        if(!productService.exitid(productXSizeDto.getProduct_idproduct())){
            return new ResponseEntity(new Mensaje("el producto no existe"),HttpStatus.BAD_REQUEST);
        }
        if(!sizeService.exitid(productXSizeDto.getSize_idsize())){
            return new ResponseEntity(new Mensaje("la talla no existe"),HttpStatus.BAD_REQUEST);
        }
        if(productXSIzeService.existbyProductSize(productService.getOne(productXSizeDto.getProduct_idproduct()).get(),sizeService.getOne(productXSizeDto.getSize_idsize()).get())){
            return new ResponseEntity<>(new Mensaje("la talla ya esta asignada"),HttpStatus.BAD_REQUEST);
        }
        ProductXSize productXSize= new ProductXSize(productXSizeDto.getTotal_amount(),productService.getOne(productXSizeDto.getProduct_idproduct()).get(),sizeService.getOne(productXSizeDto.getSize_idsize()).get());
        productXSIzeService.save(productXSize);
        return new ResponseEntity(new Mensaje("talla agregada correctamente"),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        if(!productXSIzeService.existByid(id)){
            return new ResponseEntity(new Mensaje("el producto no existe"),HttpStatus.BAD_REQUEST);
        }
        productXSIzeService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado correctamente"),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id,@RequestBody ProductXSizeDto productXSizeDto){
        if(!productXSIzeService.existByid(id)){
            return new ResponseEntity(new Mensaje("el producto no existe"),HttpStatus.BAD_REQUEST);
        }
        if((productXSIzeService.getOne(id).get().getTotal_amount() + productXSizeDto.getTotal_amount()) < 0){
            return new ResponseEntity(new Mensaje("error en stock"),HttpStatus.BAD_REQUEST);
        }
        ProductXSize productXSize= productXSIzeService.getOne(id).get();
        productXSize.setTotal_amount(productXSize.getTotal_amount()+productXSizeDto.getTotal_amount());
        productXSIzeService.save(productXSize);
        return new ResponseEntity(new Mensaje("talla actualizada correctamente"),HttpStatus.OK);
    }
}
