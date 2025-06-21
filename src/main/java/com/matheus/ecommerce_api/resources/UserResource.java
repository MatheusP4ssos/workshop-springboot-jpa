package com.matheus.ecommerce_api.resources;

import com.matheus.ecommerce_api.entities.User;
import com.matheus.ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping// Indica que este método responde a requisições HTTP POST
    public ResponseEntity<User> insert(@RequestBody User obj) {
        // @RequestBody: Converte o JSON do corpo da requisição em um objeto User
        obj = service.insert(obj);    // Chama o service para inserir o usuário no banco de dados

        // Cria a URI de localização do novo recurso
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()// Pega a URI atual (ex: /users)
                .path("/{id}").  // Adiciona /{id} ao final da URI
                        buildAndExpand(obj.getId())// Substitui {id} pelo ID real do usuário
                .toUri(); // Converte para objeto URI

        /** Retorna
         * Status 201 (Created)
         * Header "Location" com a URI do novo recurso
         * Body com o usuário criado
         */
        return ResponseEntity.created(uri).body(obj);

    }
}
