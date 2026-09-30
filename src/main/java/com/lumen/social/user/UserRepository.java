package com.lumen.social.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //ver entidade completa

    boolean existsByEmail(String email); //mais rapido, só ver se existe

    //usar findby para ver se existe, é custo de memória, tras toda a tabela
}
