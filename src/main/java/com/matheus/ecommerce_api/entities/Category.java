package com.matheus.ecommerce_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;


@Entity // @Entity: Indica que esta classe é uma entidade JPA (será uma tabela no banco)
@Table(name = "tb_category") // @Table: Especifica o nome da tabela no banco de dados

// Serializable permite que o objeto seja convertido em bytes (útil para transferência de dados)
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;   // Controle de versão da classe para serialização

    @Id // @Id: Marca este campo como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue: O banco irá gerar automaticamente os IDs
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories") //Marca a associação com a classe Product
    // Coleção que armazena os produtos pertencentes a esta categoria, usando Set para garantir que não haja duplicatas.
    // A implementação HashSet foi escolhida por sua eficiência em operações de add/remove/contains
    private Set<Product> products = new HashSet<>(); //


    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
