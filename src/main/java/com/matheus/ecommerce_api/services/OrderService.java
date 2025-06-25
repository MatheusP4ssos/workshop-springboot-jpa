package com.matheus.ecommerce_api.services;

import com.matheus.ecommerce_api.entities.Order;
import com.matheus.ecommerce_api.repositories.OrderRepository;
import com.matheus.ecommerce_api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Indica que esta classe é um serviço do Spring e deve ser gerenciada pelo container
@Service
public class OrderService {

    @Autowired    // @Autowired faz a injeção de dependência automática do repository
    // O Spring vai instanciar automaticamente um UserRepository para nós
    private OrderRepository repository;

    // Método que retorna todos os usuários do banco de dados
    public List<Order> findAll() {         // findAll() é um método já implementado pelo JpaRepository
        return repository.findAll();     // Retorna uma lista com todos os usuários

    }

    // Método que busca um usuário pelo ID
    public Order findById(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(id));

    }
}
