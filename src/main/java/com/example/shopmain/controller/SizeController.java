package com.example.shopmain.controller;

import com.example.shopmain.dto.Mensaje;
import com.example.shopmain.dto.SizeDto;
import com.example.shopmain.entity.Size;
import com.example.shopmain.service.ISizeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class SizeController {
    @Autowired
    ISizeService iSizeService;
    @GetMapping
    public ResponseEntity<List<Size>> list(){
        List<Size> list = iSizeService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Size> createsize(@RequestBody SizeDto sizeDto){
        if(StringUtils.isAllBlank(sizeDto.getName())){
            return new ResponseEntity(new Mensaje("la talla no puede estar vacia"),HttpStatus.BAD_REQUEST);
        }
        if (iSizeService.existbyname(sizeDto.getName())){
            return new ResponseEntity(new Mensaje("esa talla ya se encuentra registrada"),HttpStatus.BAD_REQUEST);
        }
        Size size = new Size(sizeDto.getName());
        iSizeService.registersize(size);
        return new ResponseEntity(new Mensaje("talla creada correctamente"),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id,@RequestBody SizeDto sizeDto) {
        if (!iSizeService.exitid(id)){
            return new ResponseEntity(new Mensaje("esa id no existe"),HttpStatus.BAD_REQUEST);
        }
        if(iSizeService.existbyname(sizeDto.getName())){
            return new ResponseEntity(new Mensaje("esa talla ya se encuentra registrada"),HttpStatus.BAD_REQUEST);
        }
        Size size =iSizeService.getOne(id).get();
        size.setName(sizeDto.getName());
        iSizeService.registersize(size);
        return new ResponseEntity(new Mensaje("talla actualizada"),HttpStatus.OK);
    }
}
