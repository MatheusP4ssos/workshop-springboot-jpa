package com.matheus.ecommerce_api.repositories;

import com.matheus.ecommerce_api.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}