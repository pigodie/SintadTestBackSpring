package com.sintad.challenge.web;

import com.sintad.challenge.model.Usuario;
import com.sintad.challenge.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
    private JWTUtil jwtUtil;

    //FAKE LOGIN SIN BASE DE DATOS, VALIDA EL TOKEN
    @PostMapping
    Usuario login(@RequestBody Usuario usuario){
        System.out.println(usuario.toString());

        if (usuario.getUser().equals("admin") && usuario.getPass().equals("admin")){
            String token=jwtUtil.create("1", usuario.getUser());
            Usuario u = new Usuario();
            u.setToken(token);
            u.setUser(usuario.getUser());
            return u;
        }
        return usuario;
    }
}
