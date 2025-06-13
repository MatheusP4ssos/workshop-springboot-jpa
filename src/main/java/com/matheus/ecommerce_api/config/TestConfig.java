package com.matheus.ecommerce_api.config;

import com.matheus.ecommerce_api.entities.Category;
import com.matheus.ecommerce_api.entities.Order;
import com.matheus.ecommerce_api.entities.Product;
import com.matheus.ecommerce_api.entities.User;
import com.matheus.ecommerce_api.entities.enums.OrderStatus;
import com.matheus.ecommerce_api.repositories.CategoryRepository;
import com.matheus.ecommerce_api.repositories.OrderRepository;
import com.matheus.ecommerce_api.repositories.ProductRepository;
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

    @Autowired
    ProductRepository productRepository;

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

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        // Salva os usuários no banco de dados usando o repositório
        // Arrays.asList cria uma lista com os usuários para serem salvos de uma vez
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));




    }
}