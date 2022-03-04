package com.example.shopmain.service;

import com.example.shopmain.dto.BuyDto;
import com.example.shopmain.dto.Mensaje;
import com.example.shopmain.entity.Buy;
import com.example.shopmain.entity.ProductXSize;
import com.example.shopmain.entity.ShoppingCar;
import com.example.shopmain.repository.BuyRepository;
import com.example.shopmain.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuyService {
    @Value("username")
    private String from;
    @Autowired
    ShoppingCarService shoppingCarService;
    @Autowired
    ProductXSIzeService productXSIzeService;
    @Autowired
    UserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    BuyRepository buyRepository;
    @Transactional
    public ResponseEntity<?> buy(BuyDto buyDto) throws MessagingException, UnsupportedEncodingException {
        boolean stock= true;
        List<ShoppingCar> shopingProducts= shoppingCarService.list(userService.findByMail(buyDto.getUser()).get());
        if (shopingProducts.isEmpty()){
            return new ResponseEntity<>(new Mensaje("Usted no tiene articulos en el carrito"),HttpStatus.BAD_REQUEST);
        }
        for (int i=0;i<shopingProducts.size();i++){
            if (((productXSIzeService.getOne(shopingProducts.get(i).getProductXSize().getId()).get().getTotal_amount()
            - shopingProducts.get(i).getAmount())<0)){
                ShoppingCar shoppingCar=  shoppingCarService.getOne(shopingProducts.get(i).getIdshopping()).get();

                shoppingCar.setAmount(productXSIzeService.getOne(shopingProducts.get(i)
                            .getProductXSize().getId()).get().getTotal_amount());
                    if (shoppingCar.getAmount()==0){
                        shoppingCarService.delete(shoppingCar.getIdshopping());
                    }

                stock=false;
            }
        }
        if (stock){
            double total = shoppingCarService.totalPrice(userService.findByMail(buyDto.getUser()).get());
            ArrayList list = new ArrayList();
            String details="";
            for (int i=0;i<shopingProducts.size();i++){
                list.add(shopingProducts.get(i).getProductXSize().getProduct().getTitulo()+" Talla: "
                        +shopingProducts.get(i).getProductXSize().getSize().getName()+"Cantidad: "
                        +shopingProducts.get(i).getAmount()+" V/U "
                        +shopingProducts.get(i).getProductXSize().getProduct().getPrice()+" Valor total del producto: "
                        +shopingProducts.get(i).getAmount()*shopingProducts.get(i).getProductXSize().getProduct().getPrice()
                        );
                ProductXSize productXSize =productXSIzeService.getOne(shopingProducts.get(i).getProductXSize().getId()).get();
                productXSize.setTotal_amount(shopingProducts.get(i).getProductXSize().getTotal_amount() - shopingProducts.get(i).getAmount());
                productXSIzeService.save(productXSize);
                shoppingCarService.delete(shopingProducts.get(i).getIdshopping());
                details=details+"<tr>"+"<td class='text-left'>"+
                        "<h3>"+shopingProducts.get(i).getProductXSize().getProduct().getTitulo()+"</h3>"+
                        shopingProducts.get(i).getProductXSize().getSize().getName()+"</td>"+
                        "<td style='text-align: center;'>"+shopingProducts.get(i).getProductXSize().getProduct().getPrice()+"</td>"+
                        "<td style='text-align: center;'>"+shopingProducts.get(i).getAmount()+"</td>"+
                        "<td style='text-align: center;'>"+shopingProducts.get(i).getAmount()*shopingProducts.get(i).getProductXSize().getProduct().getPrice()+"</td></tr>";
            }
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from, "CidenetShop");
            helper.setTo(userService.findByMail(buyDto.getUser()).get().getMail());
            helper.setSubject("Factura Cidenet Shop");



            helper.setText(
                    "<h1 style='text-align: center;'>Cidenet Shop</h1>" +
                            "<p> Factura a:"+userService.findByMail(buyDto.getUser()).get().getName()+"</p>"+
                            "<p> Fecha:"+ LocalDate.now()+"</p>"+
                            "<p> Direccion: "+buyDto.getAddress()+"</p>" +
                            "<p> Telefono: "+buyDto.getPhonenumber()+"</p>"+
                            "<h2> Total:"+total+"</h2>"+
                            "<table class='table table-invoice'>"+"<thead><tr>" +
                            "<th>Descripcion</th>" +
                            "<th>V/U</th>" +
                            "<th>Cantidad</th>" +
                            "<th>Total</th>" +
                            "</tr></thead>"+
                            "<tbody>"+
                            details+
                            "</tbody>"+
                            "</table>",
                    true);
            Buy buy =new Buy(buyDto.getAddress(), buyDto.getCity(), buyDto.getCpostal(), buyDto.getPhonenumber(),list.toString(), LocalDateTime.now(),userService.findByMail(buyDto.getUser()).get());
            buyRepository.save(buy);
            mailSender.send(message);
            return new ResponseEntity<>(new Mensaje("compra realizada con exito"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Mensaje("su carrito fue modificado por favor reviselo"),HttpStatus.BAD_REQUEST);
        }
    }
}
