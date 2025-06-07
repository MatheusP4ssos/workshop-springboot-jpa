package com.matheus.ecommerce_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication é uma anotação que combina:
// - @Configuration: marca a classe como fonte de definições de beans
// - @EnableAutoConfiguration: configura automaticamente a aplicação baseado nas dependências
// - @ComponentScan: permite que o Spring procure outros componentes, configurações e serviços
@SpringBootApplication
public class ECommerceApiApplication {
    // Método principal que inicia a aplicação Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(ECommerceApiApplication.class, args);
    }
}