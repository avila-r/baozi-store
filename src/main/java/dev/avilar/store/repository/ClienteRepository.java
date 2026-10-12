package dev.avilar.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.avilar.store.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
