package com.matheus.ecommerce_api.config;

import com.matheus.ecommerce_api.entities.User;
import com.matheus.ecommerce_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

// Marca esta classe como uma classe de configuração do Spring
@Configuration
// Define que esta configuração só será ativa quando o perfil "test" estiver ativo
@Profile("test")
public class TestConfig implements CommandLineRunner {

    // Injeta o repositório de usuários automaticamente
    @Autowired
    private UserRepository userRepository;

    // Método executado automaticamente quando a aplicação é iniciada
    @Override
    // Código para popular o banco de dados com dados de teste
    public void run(String... args) throws Exception {
        // Cria dois usuários para teste
        // O primeiro parâmetro (null) é o ID, que será gerado automaticamente
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "(21)9856-12657", "72583882");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "(21)9346-15457", "734566312");

        // Salva os usuários no banco de dados usando o repositório
        // Arrays.asList cria uma lista com os usuários para serem salvos de uma vez
        userRepository.saveAll(Arrays.asList(u1, u2));


    }
}