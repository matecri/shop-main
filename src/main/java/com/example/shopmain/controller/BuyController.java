package com.example.shopmain.controller;



import com.example.shopmain.dto.BuyDto;
import com.example.shopmain.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/Buy")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class BuyController {
    @Autowired
    BuyService buyService;
    @PostMapping("/buy")
    public ResponseEntity list(@RequestBody BuyDto buyDto) throws MessagingException, UnsupportedEncodingException {
        return buyService.buy(buyDto);
    }

}
