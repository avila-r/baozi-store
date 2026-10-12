package dev.avilar.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.avilar.store.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
