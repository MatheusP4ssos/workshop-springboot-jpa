package com.matheus.ecommerce_api.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity// @Entity: Indica que esta classe é uma entidade JPA (será uma tabela no banco)
@Table(name = "tb_products") // @Table: Especifica o nome da tabela no banco de dados

// Serializable permite que o objeto seja convertido em bytes (útil para transferência de dados)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L; // Controle de versão da classe para serialização

    @Id // @Id: Marca este campo como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue: O banco irá gerar automaticamente os IDs
    private Long id;
    private String name;
    private String description; // Corrigido o nome do campo
    private Double price;
    private String imgUrl;

    @ManyToMany // Define a associação com a classe Category (Muitos para Muitos)
    @JoinTable(
            // Define o nome da tabela intermediária que será criada para gerenciar
            // o relacionamento many-to-many entre Produto e Categoria
            name = "tb_product_category",

            // Define a coluna que será a chave estrangeira referenciando a tabela de Produto
            // Esta coluna armazenará os IDs dos produtos
            joinColumns = @JoinColumn(name = "product_id"),

            // Define a coluna que será a chave estrangeira referenciando a tabela de Categoria
            // Esta coluna armazenará os IDs das categorias
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    // Coleção que armazena os produtos pertencentes a esta categoria, usando Set para garantir que não haja duplicatas.
    // A implementação HashSet foi escolhida por sua eficiência em operações de add/remove/contains
    private Set<Category> categories = new HashSet<>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}