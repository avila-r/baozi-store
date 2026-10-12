package dev.avilar.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// request body for creating or updating an order
public record PedidoRequest(@NotNull Long clienteId, @NotNull Long produtoId, @NotNull @Positive Integer quantidade) {
}
