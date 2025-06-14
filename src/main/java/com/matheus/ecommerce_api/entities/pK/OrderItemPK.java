package com.matheus.ecommerce_api.entities.pK;

import com.matheus.ecommerce_api.entities.Order;
import com.matheus.ecommerce_api.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que representa a chave primária composta da tabela de itens de pedido.
 * Esta classe é necessária para estabelecer um relacionamento many-to-many com atributos adicionais
 * entre Order (Pedido) e Product (Produto).
 */

@Embeddable// Indica que esta classe será incorporada em outra entidade
public class OrderItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    // Relacionamento muitos-para-um com Order
    @ManyToOne
    @JoinColumn(name = "order_id") // Define a coluna de chave estrangeira para Order
    private Order order;

    // Relacionamento muitos-para-um com Product
    @ManyToOne
    @JoinColumn(name = "product_id") //Define a coluna de chave estrangeira para Product
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Implementação de equals usando os dois campos (order e product)
    // Necessário para comparação correta dos objetos
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderItemPK that)) return false;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    // Implementação de hashCode usando os dois campos
    // Necessário para uso em coleções (HashSet, HashMap)
    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
