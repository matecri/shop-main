package com.example.shopmain.security.controller;

import com.example.shopmain.dto.Mensaje;
import com.example.shopmain.security.dto.JwtDto;
import com.example.shopmain.security.dto.LoginUser;
import com.example.shopmain.security.dto.NewUser;
import com.example.shopmain.security.entity.Rol;
import com.example.shopmain.security.entity.User;
import com.example.shopmain.security.enums.RolName;
import com.example.shopmain.security.jwt.JwtProvider;
import com.example.shopmain.security.service.RolService;
import com.example.shopmain.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    @PostMapping("/newuser")
    public ResponseEntity<?> newuser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByMail(newUser.getMail())){
            return new ResponseEntity(new Mensaje("el correo ya esta registrado"),HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByDocument(newUser.getDocument())){
            return new ResponseEntity(new Mensaje("el Documento ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        User user =new User(newUser.getName(), newUser.getLastname(), newUser.getMail(), passwordEncoder.encode(newUser.getPass()), newUser.getAddress(), newUser.getDocument(), newUser.getType_document());
        Set<Rol> roles =new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin")){
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Mensaje("Usuario creado satisfactoriamente"),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("datos invalida"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getMail(),loginUser.getPass()));
        SecurityContextHolder.getContext().setAuthentication((authentication));
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user= userService.findByMail(loginUser.getMail()).get();
        Long iduser = user.getIdUser();
        String bearer ="Bearer";
        JwtDto jwtDto =new JwtDto(jwt,bearer,userDetails.getUsername(),userDetails.getAuthorities(),iduser);
        return new ResponseEntity(jwtDto,HttpStatus.OK);
    }
}
