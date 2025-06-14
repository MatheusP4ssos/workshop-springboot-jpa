package com.matheus.ecommerce_api.entities;

import com.matheus.ecommerce_api.entities.pK.OrderItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que representa os itens de um pedido.
 * Cada OrderItem é uma linha em um pedido, contendo um produto específico,
 * sua quantidade e preço.
 */

@Entity // @Entity: Indica que esta classe é uma entidade JPA (será uma tabela no banco)
@Table(name = "tb_order_item") // @Table: Especifica o nome da tabela no banco de dados

// Serializable permite que o objeto seja convertido em bytes (útil para transferência de dados)
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;  // Controle de versão da classe para serialização

    // Usa a chave composta definida em OrderItemPK
    @EmbeddedId
    private OrderItemPK id;

    private Integer quantity;    // Quantidade do produto no pedido
    private Double price;    // Preço do produto no momento da compra

    public OrderItem() {
    }

    /**
     * Construtor que inicializa um item de pedido
     * @param order Pedido ao qual este item pertence
     * @param product Produto que está sendo comprado
     * @param quantity Quantidade do produto
     * @param price Preço unitário do produto
     */

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    // Métodos de acesso para Order através da chave composta
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    // Métodos de acesso para Product através da chave composta
    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Calcula o subtotal deste item de pedido
     * @return O valor total (preço * quantidade)
     */

    public Double subtotal() {
        return this.price * this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
