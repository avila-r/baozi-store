package dev.avilar.store.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.avilar.store.dto.PedidoRequest;
import dev.avilar.store.model.Pedido;
import dev.avilar.store.model.Produto;
import dev.avilar.store.repository.ClienteRepository;
import dev.avilar.store.repository.PedidoRepository;
import dev.avilar.store.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository repository;
    private final ClienteRepository clientes;
    private final ProdutoRepository produtos;

    public PedidoController(PedidoRepository repository, ClienteRepository clientes, ProdutoRepository produtos) {
        this.repository = repository;
        this.clientes = clientes;
        this.produtos = produtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido create(@Valid @RequestBody PedidoRequest request) {
        Pedido pedido = new Pedido();
        apply(pedido, request);
        return repository.save(pedido);
    }

    @GetMapping
    public List<Pedido> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido findById(@PathVariable Long id) {
        return find(id);
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable Long id, @Valid @RequestBody PedidoRequest request) {
        Pedido pedido = find(id);
        apply(pedido, request);
        return repository.save(pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.delete(find(id));
    }

    private void apply(Pedido pedido, PedidoRequest request) {
        pedido.setCliente(clientes.findById(request.clienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found")));
        Produto produto = produtos.findById(request.produtoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found"));
        if (!Boolean.TRUE.equals(produto.getEstoque())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product out of stock");
        }
        pedido.setProduto(produto);
        pedido.setQuantidade(request.quantidade());
    }

    private Pedido find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }
}
