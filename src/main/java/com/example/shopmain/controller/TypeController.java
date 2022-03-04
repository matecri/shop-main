package com.example.shopmain.controller;

import com.example.shopmain.dto.Mensaje;
import com.example.shopmain.dto.TypeDto;
import com.example.shopmain.entity.Type;
import com.example.shopmain.service.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class TypeController {
    @Autowired
    TypeService iTypeService;
    @GetMapping("/list")
    public ResponseEntity<List<Type>> list(){
        List<Type> list = iTypeService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Type> CreateType(@RequestBody TypeDto typeDto) {
        if(StringUtils.isAllBlank(typeDto.getName())){
            return new ResponseEntity(new Mensaje("el nombre no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        if(iTypeService.existbyname(typeDto.getName())){
            return new ResponseEntity(new Mensaje("ese dato ya esta guardado"),HttpStatus.BAD_REQUEST);
        }
        Type type= new Type(typeDto.getName());
       iTypeService.registertype(type);
       return new ResponseEntity(new Mensaje("Tipo creado"),HttpStatus.OK);

    }

}
