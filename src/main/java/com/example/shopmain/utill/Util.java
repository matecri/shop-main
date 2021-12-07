/*package com.example.shopmain.utill;

import com.example.shopmain.entity.Size;
import com.example.shopmain.entity.Type;
import com.example.shopmain.security.entity.Rol;
import com.example.shopmain.security.enums.RolName;
import com.example.shopmain.security.service.RolService;
import com.example.shopmain.service.SizeService;
import com.example.shopmain.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Util implements CommandLineRunner {
    @Autowired
    RolService rolService;
    @Autowired
    SizeService sizeService;
    @Autowired
    TypeService typeService;
    @Override
    public void run(String... args) throws Exception {
        Rol roladmin =new Rol(RolName.ROLE_ADMIN);
        Rol roluser =new Rol(RolName.ROLE_USER);
        rolService.save(roladmin);
        rolService.save(roluser);
        Size s =new Size("S");
        sizeService.registersize(s);
        Size m =new Size("M");
        sizeService.registersize(m);
        Size L =new Size("L");
        sizeService.registersize(L);
        Size XL =new Size("XL");
        sizeService.registersize(XL);
        Type hombre=new Type("Hombre");
        typeService.registertype(hombre);
        Type mujer=new Type("Mujer");
        typeService.registertype(mujer);

    }
}
*/