package com.matheus.ecommerce_api.repositories;

import com.matheus.ecommerce_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsável pela persistência e operações de banco de dados relacionadas à entidade User.
 *
 * Ao estender JpaRepository<Category, Long>, esta interface herda automaticamente operações CRUD padrão:
 * - save(): Salva uma categoria
 * - findById(): Busca uma categoria pelo ID
 * - findAll(): Retorna todas as categorias
 * - delete(): Remove uma categoria
 * - count(): Conta o número total de categorias
 * - existsById(): Verifica se existe uma categoria com determinado ID
 *
 * @see User
 * @see JpaRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
