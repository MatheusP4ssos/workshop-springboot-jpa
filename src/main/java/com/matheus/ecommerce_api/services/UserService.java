package com.matheus.ecommerce_api.services;

import com.matheus.ecommerce_api.entities.User;
import com.matheus.ecommerce_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        // repository.findById() retorna um Optional<User>
        Optional<User> obj = repository.findById(id); // repository.findById() retorna um Optional<User>

        // obj.get() retorna o usuário se existir
        return obj.get();
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User obj) {
        // getReferenceById retorna uma referência proxy, que só acessa o banco quando necessário
        User entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    // Atualiza os dados permitidos do usuário: nome, email, senha e telefone
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPassword(obj.getPassword());
        entity.setPhone(obj.getPhone());
    }
}
