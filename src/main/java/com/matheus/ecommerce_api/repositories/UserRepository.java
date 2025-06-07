package com.matheus.ecommerce_api.repositories;

import com.matheus.ecommerce_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
