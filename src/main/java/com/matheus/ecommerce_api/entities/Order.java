package com.matheus.ecommerce_api.entities;


import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity// @Entity: Indica que esta classe é uma entidade JPA (será uma tabela no banco)
@Table(name = "tb_orders")// Renomeia o nome da tabela no banco de dados para "tb_orders" evitando problemas com palavras reservadas

public class Order implements Serializable { //Serializable permite que o objeto seja convertido em Bytes(útil para transferência de dados)
    // Controle de versão da classe para serialização
    private static final long serialVersionUID = 1L;

    @Id // @Id: Marca este campo como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue: O banco irá gerar automaticamente os IDs
    private long id;
    private Instant moment;

    @ManyToOne // Define a associação com a classe User
    @JoinColumn(name = "client_id") //Define o nome da coluna na tabela que representa a chave estrangeira
    private User client; //Implementa a associação com a classe User

    public Order() {
    }

    public Order(long id, Instant moment, User client) {
        this.id = id;
        this.moment = moment;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
