package com.matheus.ecommerce_api.entities;


import jakarta.persistence.*;

import java.io.Serializable;

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
    private String password;
    
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