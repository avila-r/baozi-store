package dev.avilar.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.avilar.store.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
