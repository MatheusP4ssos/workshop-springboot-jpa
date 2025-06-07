package com.matheus.ecommerce_api.resources;

import com.matheus.ecommerce_api.entities.User;
import com.matheus.ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController: Combina @Controller e @ResponseBody, indica que esta classe é um controlador REST
@RestController
// @RequestMapping: Define o endpoint base para todos os métodos desta classe
@RequestMapping(value = "/users")

public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping    // @GetMapping: Mapeia requisições HTTP GET para este método
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

     // @GetMapping(value = "/{id}"): Mapeia requisições HTTP GET para o endpoint /users/{id}
    // O {id} é uma variável de caminho que será substituída pelo valor real na URL
    @GetMapping(value = "/{id}")
    // @PathVariable ("id"): Indica que o parâmetro 'id' do método deve ser extraído
    // da variável de caminho {id} na URL
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        // Chama o serviço para buscar o usuário pelo ID fornecido
        User obj = service.findById(id);
        // ResponseEntity.ok(): Cria uma resposta HTTP com status 200 (OK)
        // .body(obj): Define o corpo da resposta como o objeto usuário encontrado
        // O objeto será automaticamente convertido para JSON
        return ResponseEntity.ok().body(obj);
    }
}
