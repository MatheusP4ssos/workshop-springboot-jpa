package com.matheus.ecommerce_api.config;

import com.matheus.ecommerce_api.entities.Category;
import com.matheus.ecommerce_api.entities.Order;
import com.matheus.ecommerce_api.entities.User;
import com.matheus.ecommerce_api.entities.enums.OrderStatus;
import com.matheus.ecommerce_api.repositories.CategoryRepository;
import com.matheus.ecommerce_api.repositories.OrderRepository;
import com.matheus.ecommerce_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

// Marca esta classe como uma classe de configuração do Spring
@Configuration
// Define que esta configuração só será ativa quando o perfil "test" estiver ativo
@Profile("test")
public class TestConfig implements CommandLineRunner {

    // Injeta o repositório de usuários automaticamente
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Método executado automaticamente quando a aplicação é iniciada
    @Override
    // Código para popular o banco de dados com dados de teste
    public void run(String... args) throws Exception {
        // Cria dois usuários para teste
        // O primeiro parâmetro (null) é o ID, que será gerado automaticamente
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "(21)9856-12657", "72583882");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "(21)9346-15457", "734566312");

        // Primeiro pedido (original)
        Order o1 = new Order(null, Instant.parse("2025-06-20T19:53:06Z"), OrderStatus.PAID, u1);

        // Segundo pedido (3 dias depois)
        Order o2 = new Order(null, Instant.parse("2025-06-23T15:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);

        // Terceiro pedido (mesmo dia que o segundo, mas mais tarde)
        Order o3 = new Order(null, Instant.parse("2025-06-23T21:15:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        // Salva os usuários no banco de dados usando o repositório
        // Arrays.asList cria uma lista com os usuários para serem salvos de uma vez
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));




    }
}