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

    // Injeta o repositório de pedidos automaticamente
    @Autowired
    private OrderRepository orderRepository;

    //Injeta o repositório de categorias automaticamente
    @Autowired
    private CategoryRepository categoryRepository;

    //Injeta o repositório de produtos automaticamente
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

        //Cria as categorias disponíveis para os produtos
        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        //Popula o banco de dados com Produtos
        Product p1 = new Product(
                null,
                "The Lord of the Rings",
                "Trilogia épica de fantasia escrita por J.R.R. Tolkien. Box set completo com os três livros da série: A Sociedade do Anel, As Duas Torres e O Retorno do Rei. Edição especial capa dura com ilustrações.",
                90.5,
                "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"
        );
        Product p2 = new Product(
                null,
                "Smart TV Samsung 55\"",
                "Smart TV LED 55 polegadas, resolução 4K, HDR, com sistema Tizen, conexão WiFi, 3 entradas HDMI, 2 USB. Compatível com Alexa e Google Assistant. Tecnologia Crystal UHD para cores mais vivas.",
                2190.0,
                "https://images.samsung.com/is/image/samsung/p6pim/br/un55bu8000gxzd/gallery/br-crystal-uhd-bu8000-un55bu8000gxzd-533187293?$650_519_PNG$"
        );
        Product p3 = new Product(
                null,
                "Macbook Pro M2",
                "MacBook Pro 14 polegadas com chip M2 Pro, 16GB RAM, SSD 512GB. Tela Liquid Retina XDR, Touch ID, Magic Keyboard retroiluminado. Bateria com até 18 horas de duração.",
                1250.0,
                "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mbp14-spacegray-select-202301?wid=904&hei=840&fmt=jpeg&qlt=90&.v=1671304673229"
        );
        Product p4 = new Product(
                null,
                "PC Gamer Ultimate",
                "Computador gaming com processador Intel i9 13900K, 32GB RAM DDR5, placa de vídeo RTX 4080 16GB, SSD NVMe 2TB, fonte 850W 80 Plus Gold. Gabinete com refrigeração líquida e RGB.",
                1200.0,
                "https://images.kabum.com.br/produtos/fotos/sync_mirakl/429755/Pc-Gamer-Completo-Intel-Core-I9-16gb-Ram-Ssd-480gb-Rtx-3060-12gb-Monitor-27-Led-Mouse-E-Teclado_1683925595_g.jpg"
        );
        Product p5 = new Product(
                null,
                "Rails for Dummies",
                "Guia completo e atualizado sobre Ruby on Rails 7.0. Aprenda desde conceitos básicos até técnicas avançadas de desenvolvimento web. Inclui exemplos práticos, dicas de segurança e boas práticas.",
                100.99,
                "https://m.media-amazon.com/images/I/51TfVxYqwQL._SX404_BO1,204,203,200_.jpg"
        );

        //Associa os Produtos as suas respectivas categorias
        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);


        // Salva os adições realizadas nos usuários, pedidos, categorias e produtos
        // Arrays.asList cria uma lista com os usuários para serem salvos de uma vez
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));




    }
}