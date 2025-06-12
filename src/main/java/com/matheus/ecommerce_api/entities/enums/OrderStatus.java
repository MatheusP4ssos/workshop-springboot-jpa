package com.matheus.ecommerce_api.entities.enums;

/**
 * Enum que representa os possíveis estados de um pedido no sistema de e-commerce.
 * Os estados seguem uma progressão lógica do ciclo de vida do pedido.
 */
public enum OrderStatus {
    WAITING_PAYMENT(1), //Aguardando pagamento, status inicial do pedido
    PAID(2), //Pagamento confirmado e processado com sucesso
    SHIPPED(3), //Pedido embalado e enviado para entrega
    DELIVERED(4), //Pedido entregue ao cliente com sucesso
    CANCELED(5); //Pedido cancelado pelo cliente ou pelo sistema de e-commerce em geral

    private int code;

    /**
     * Construtor privado do enum.
     * @param code Código numérico do status
     */
    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * Converte um código numérico para o enum OrderStatus correspondente.
     * @param code Código a ser convertido
     * @return OrderStatus correspondente ao código
     * @throws IllegalArgumentException se o código for inválido
     */
    public static OrderStatus valueOf(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid order status code: " + code);
    }
}
