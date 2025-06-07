package com.matheus.ecommerce_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Adiciona esta anotação para ativar o perfil de teste
class ECommerceApiApplicationTests {

    @Test
    void contextLoads() {
    }

}