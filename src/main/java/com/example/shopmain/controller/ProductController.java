package com.example.shopmain.controller;

import com.example.shopmain.criteria.ProductCriteria;
import com.example.shopmain.dto.Mensaje;
import com.example.shopmain.dto.ProductDto;
import com.example.shopmain.dto.SearchDto;
import com.example.shopmain.entity.Product;
import com.example.shopmain.service.ProductService;
import com.example.shopmain.service.TypeService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class ProductController {
    @Autowired
    ProductService IProductService;
    @Autowired
    TypeService iTypeService;
    @PostMapping("/list")
    public ResponseEntity<List<Product>> list(@RequestBody SearchDto searchDto) {
        ProductCriteria productCriteria =createCriteria(searchDto);
        BooleanFilter filter=new BooleanFilter();
        filter.setEquals(true);
        productCriteria.setMostar(filter);
        List<Product> list =IProductService.findByCriteriaUser(productCriteria);
        return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adminlist")
    public ResponseEntity<List<Product>> listadmin(@RequestBody SearchDto searchDto) {
        ProductCriteria productCriteria =createCriteria(searchDto);
        List<Product> list =IProductService.findByCriteriaUser(productCriteria);
        return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
    }
    private ProductCriteria createCriteria(SearchDto dto){
        ProductCriteria productCriteria =new ProductCriteria();
        if(dto!=null){
            if(!StringUtils.isBlank(dto.getBrand())){
                StringFilter filter =new StringFilter();
                filter.setContains(dto.getBrand());
                productCriteria.setBrand(filter);
            }
            if(!StringUtils.isBlank(dto.getColor())){
                StringFilter filter =new StringFilter();
                filter.setContains(dto.getColor());
                productCriteria.setColor(filter);
            }
            if(!StringUtils.isBlank(dto.getTitulo())){
                StringFilter filter =new StringFilter();
                filter.setContains(dto.getTitulo());
                productCriteria.setTitulo(filter);
            }
            if(!StringUtils.isBlank(dto.getType())){
                StringFilter filter =new StringFilter();
                filter.setEquals(dto.getType());
                productCriteria.setType(filter);
            }
        }
        return productCriteria;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        if(StringUtils.isAllBlank(productDto.getBrand())){
            return new ResponseEntity(new Mensaje("la marca es obligatoria"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getDescrip())){
            return new ResponseEntity(new Mensaje("la Descripcion es obligatoria"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getImg())){
            return new ResponseEntity(new Mensaje("Por favor suba una imagen de producto"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getColor())){
            return new ResponseEntity(new Mensaje("el color es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getTitulo())){
            return new ResponseEntity(new Mensaje("el titulo es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(productDto.getMostrar()==null){
            return new ResponseEntity(new Mensaje("error en campo mostrar"),HttpStatus.BAD_REQUEST);
        }
        if (productDto.getPrice()<0){
            return new ResponseEntity(new Mensaje("valor invalido"),HttpStatus.BAD_REQUEST);
        }
        if(IProductService.existsBydescrip(productDto.getDescrip()) && IProductService.existsBybrand(productDto.getBrand()) && IProductService.existsBycolor(productDto.getColor())){
            return new ResponseEntity(new Mensaje("ya existe un producto con las mismas caracteristicas"),HttpStatus.BAD_REQUEST);
        }
        if (productDto.getType()==null){
            return new ResponseEntity(new Mensaje("eliga un tipo"),HttpStatus.BAD_REQUEST);
        }
        if(!iTypeService.existbyid(productDto.getType())){
            return new ResponseEntity(new Mensaje("el tipo no existe"),HttpStatus.BAD_REQUEST);
        }
        Product product =new Product(productDto.getImg(), productDto.getTitulo(), productDto.getDescrip(),productDto.getBrand(),productDto.getColor(),productDto.getPrice(),productDto.getMostrar(),iTypeService.getOne(productDto.getType()).get());
        product.setPviews(0L);
         IProductService.save(product);
          return new ResponseEntity(new Mensaje("Producto Creado"),HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id")Long id){
        if (!IProductService.exitid(id)){
            return new ResponseEntity(new Mensaje("el producto no existe"),HttpStatus.BAD_REQUEST);
        }
        Product product = IProductService.getOne(id).get();
        if(product.getPviews()==null){
           return new ResponseEntity(new Mensaje("error contacte con un admin"),HttpStatus.BAD_REQUEST);
        }
        product.setPviews(product.getPviews() + 1L);
        IProductService.save(product);
        return new ResponseEntity(product,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id,@RequestBody ProductDto productDto){
        if(!IProductService.exitid(id)){
            return new ResponseEntity(new Mensaje("el producto no existe"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getTitulo())){
            return new ResponseEntity(new Mensaje("el titulo es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getBrand())){
            return new ResponseEntity(new Mensaje("la marca es obligatoria"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getDescrip())){
            return new ResponseEntity(new Mensaje("la Descripcion es obligatoria"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getImg())){
            return new ResponseEntity(new Mensaje("Por favor suba una imagen de producto"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isAllBlank(productDto.getColor())){
            return new ResponseEntity(new Mensaje("el color es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(productDto.getMostrar()==null){
            return new ResponseEntity(new Mensaje("error en campo mostrar"),HttpStatus.BAD_REQUEST);
        }
        if (productDto.getPrice()<0){
            return new ResponseEntity(new Mensaje("valor invalido"),HttpStatus.BAD_REQUEST);
        }
        if(!iTypeService.existbyid(productDto.getType())){
            return new ResponseEntity(new Mensaje("el tipo no existe"),HttpStatus.BAD_REQUEST);
        }
        Product product = IProductService.getOne(id).get();
        product.setPrice(productDto.getPrice());
        product.setTitulo(productDto.getTitulo());
        product.setBrand(productDto.getBrand());
        product.setDescrip(productDto.getDescrip());
        product.setImg(productDto.getImg());
        product.setColor(productDto.getColor());
        product.setMostrar(productDto.getMostrar());
        product.setType(iTypeService.getOne(productDto.getType()).get());

        IProductService.save(product);
        return new ResponseEntity(new Mensaje("Producto actualizado"),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        if (!IProductService.exitid(id)){
            return new ResponseEntity(new Mensaje("el producto no existe"),HttpStatus.BAD_REQUEST);
        }
        IProductService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    @GetMapping("/popular")
    public ResponseEntity<List<Product>> popular(){
        List<Product> popular = IProductService.popular();
        return new ResponseEntity<List<Product>>(popular,HttpStatus.OK);
    }
}
