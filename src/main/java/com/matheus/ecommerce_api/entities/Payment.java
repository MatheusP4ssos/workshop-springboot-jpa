package com.matheus.ecommerce_api.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity//@Entity define que essa classe é uma entidade JPA(será uma tabela no banco)
@Table(name = "tb_payments")//@Table define o nome da tabela no banco de dados

// Serializable permite que o objeto seja convertido em bytes (útil para transferência de dados)
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // @Id: Marca este campo como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue: O banco irá gerar automaticamente os IDs
    private Long id;

    private Instant moment;

    //Relacionamento um para um com Order(um pedido pode ter um pagamento associado)
    @OneToOne
    @MapsId //Faz com que a entidade Payment use o mesmo ID que a entidade Order
    private Order order;

    public Payment() {

    }

    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Payment payment)) return false;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
