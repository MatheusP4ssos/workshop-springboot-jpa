package com.matheus.ecommerce_api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.ecommerce_api.entities.enums.OrderStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity // @Entity: Indica que esta classe é uma entidade JPA (será uma tabela no banco)
@Table(name = "tb_orders") // @Table: Especifica o nome da tabela no banco de dados

// Serializable permite que o objeto seja convertido em bytes (útil para transferência de dados)
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;  // Controle de versão da classe para serialização

    @Id // @Id: Marca este campo como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue: O banco irá gerar automaticamente os IDs
    private Long id;

    // Define o formato da data que será mostrado no JSON:
    // - shape = STRING: formata como texto
    // - pattern: define o padrão ISO 8601 (yyyy-MM-dd'T'HH:mm:ss'Z')
    // - timezone = "GMT": define o fuso horário como GMT/UTC
    @JsonFormat(shape = JsonFormat.Shape.STRING, 
                pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", 
                timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;


    // Define relacionamento muitos-para-um com User
    // Um pedido pertence a um cliente
    // JoinColumn define a coluna de chave estrangeira
    @ManyToOne
    @JoinColumn(name = "client_id") //Define o nome da coluna na tabela de relacionamento
    private User client;

    //Define um relacionamento um-para-muitos com OrderItem(Um pedido pode ter múltiplos itens)
    @OneToMany(mappedBy = "id.order") // Relacionamento já mapeado pela classe OrderItem (OrderItemPK)
    private Set<OrderItem> items = new HashSet<>(); //@Set evita elementos duplicados na lista

    // Define relacionamento um-para-um com Payment
    // Um pedido pode ter um pagamento associado
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)//Define que todas as operações realizadas em um Order serão
    //propagadas para o Payment associado ao mesmo (cascade = ALL).
    private Payment payment;

    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {  // Alterado de 'long' para 'Long'
        this.id = id;
        setOrderStatus(orderStatus);
        this.moment = moment;
        this.client = client;
    }

    public Long getId() {  // Alterado de 'long' para 'Long'
        return id;
    }

    public void setId(Long id) {  // Alterado de 'long' para 'Long'
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    /**
     * Define o status de um pedido
     *
     * @param orderStatus O novo status do pedido (enum OrderStatus)
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        // Verifica se o parâmetro não é nulo para evitar NullPointerException
        if (orderStatus != null) {
            // Armazena apenas o código numérico do status
            // Exemplo: se orderStatus for PAID (2), apenas o número 2 será armazenado
            // Isso economiza espaço no banco de dados
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    /**
     * Calcula o valor total do pedido
     *
     * @return O valor total somando todos os preços dos itens no pedido
     */
    public double getTotal () {
        double total = 0.0; // Inicializa variável para acumular o valor total
        // Itera sobre cada item do pedido
        for (OrderItem item : items) {
            total += item.getSubtotal();  // Adiciona o preço de cada item ao total
        }
        return total;// Retorna o valor total calculado
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}