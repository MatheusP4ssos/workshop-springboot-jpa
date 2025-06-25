package com.matheus.ecommerce_api.services;

import com.matheus.ecommerce_api.entities.Category;
import com.matheus.ecommerce_api.entities.Product;
import com.matheus.ecommerce_api.repositories.ProductRepository;
import com.matheus.ecommerce_api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Indica que esta classe é um serviço do Spring e deve ser gerenciada pelo container
@Service
public class ProductService {

    @Autowired    // @Autowired faz a injeção de dependência automática do repository
    // O Spring vai instanciar automaticamente um UserRepository para nós
    private ProductRepository repository;

    // Método que retorna todos os usuários do banco de dados
    public List<Product> findAll() {         // findAll() é um método já implementado pelo JpaRepository
        return repository.findAll();     // Retorna uma lista com todos os usuários

    }

    // Método que busca um usuário pelo ID
    public Product findById(Long id) {
        // repository.findById() retorna um Optional<User>
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
