package com.matheus.ecommerce_api.services;

import com.matheus.ecommerce_api.entities.User;
import com.matheus.ecommerce_api.repositories.UserRepository;
import com.matheus.ecommerce_api.services.exceptions.DatabaseException;
import com.matheus.ecommerce_api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

// Indica que esta classe é um serviço do Spring e deve ser gerenciada pelo container
@Service
public class UserService {

    @Autowired    // @Autowired faz a injeção de dependência automática do repository
    // O Spring vai instanciar automaticamente um UserRepository para nós
    private UserRepository repository;

    // Método que retorna todos os usuários do banco de dados
    public List<User> findAll() {         // findAll() é um método já implementado pelo JpaRepository
        return repository.findAll();     // Retorna uma lista com todos os usuários

    }

    // Método que busca um usuário pelo ID
    public User findById(Long id) {
        // Busca um usuário no repositório pelo ID fornecido
        // Se o usuário não for encontrado, lança uma exceção ResourceNotFounfException
        // incluindo o ID que não foi encontrado na mensagem de erro
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        //Verifica primeiro a existência do usuário no banco de dados
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        try {
            User entity = repository.getReferenceById(id);
            
            // Validação de dados nulos
            if (obj == null) {
                throw new IllegalArgumentException("User object cannot be null");
            }

            // Validação de campos obrigatórios
            if (obj.getEmail() == null || obj.getEmail().trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }
            if (obj.getName() == null || obj.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }

            try {
                updateData(entity, obj);
                return repository.save(entity);
            } catch (DataIntegrityViolationException e) {
                // Violação de restrições do banco (ex: email duplicado)
                throw new DatabaseException("Database integrity error: " + e.getMessage());
            } catch (OptimisticLockingFailureException e) {
                // Erro de concorrência
                throw new DatabaseException("Record was modified by another user");
            }
            
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (IllegalArgumentException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Atualiza os dados permitidos do usuário: nome, email, senha e telefone
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPassword(obj.getPassword());
        entity.setPhone(obj.getPhone());
    }
}