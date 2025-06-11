package com.matheus.ecommerce_api.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity// @Entity: Indica que esta classe é uma entidade JPA (será uma tabela no banco)
@Table(name = "tb_users")// @Table: Especifica o nome da tabela no banco de dados

// Serializable permite que o objeto seja convertido em bytes (útil para transferência de dados)
public class User implements Serializable {
    // Controle de versão da classe para serialização
    private static final long serialVersionUID = 1L;

    @Id    // @Id: Marca este campo como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // @GeneratedValue: O banco irá gerar automaticamente os IDs

    private Long id;

    // Campos da entidade (viram colunas na tabela)
    private String name;
    private String email;
    private String phone;

    // Permite apenas receber a senha nas requisições (POST/PUT)
    // mas não permite que ela seja enviada nas respostas
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // Método público que retorna a senha mascarada com asteriscos
    // Este será usado na serialização JSON
    @JsonProperty("password")
    public String getMaskedPassword() {
        return password == null ? null : "*".repeat(8);
    }

    @JsonIgnore // Evita loop infinito na serialização JSON
    @OneToMany(mappedBy = "client") //Define a associação com a classe Order
    private List<Order> orders = new ArrayList<>(); //Imlementa a associação com a classe Order

    public User() {
    }

    // Construtores, getters e setters...
    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Método privado para acesso interno à senha real
    private String getPasswordInternal() {
        return password;
    }



    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
