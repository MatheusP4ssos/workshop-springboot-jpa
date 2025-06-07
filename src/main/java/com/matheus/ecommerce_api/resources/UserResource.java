package com.matheus.ecommerce_api.resources;

import com.matheus.ecommerce_api.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController: Combina @Controller e @ResponseBody, indica que esta classe é um controlador REST
@RestController
// @RequestMapping: Define o endpoint base para todos os métodos desta classe
@RequestMapping(value = "/users")

public class UserResource {

    @GetMapping    // @GetMapping: Mapeia requisições HTTP GET para este método
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Jeferson Felipe dos Santos", "Jeferson.Fp@gmail.com", "(21)9856-12657", "72583882");
        return ResponseEntity.ok().body(u);
    }
}
