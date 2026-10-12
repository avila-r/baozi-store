# Store API

A simple REST API built with Spring Boot, Spring Data JPA and MySQL. It manages customers (`clientes`), products (`produtos`) and orders (`pedidos`), where each order links one customer to one product with a quantity.

## Running

```bash
docker compose up -d
./mvnw spring-boot:run
```

The API runs on `http://localhost:8080`. Tables are created automatically on startup.

## Endpoints

Each resource (`/clientes`, `/produtos`, `/pedidos`) supports:

| Method | Path | Description |
|---|---|---|
| POST | `/{resource}` | create |
| GET | `/{resource}` | list all |
| GET | `/{resource}/{id}` | find by id |
| PUT | `/{resource}/{id}` | update |
| DELETE | `/{resource}/{id}` | delete |

Creating an order:

```json
POST /pedidos
{ "clienteId": 1, "produtoId": 1, "quantidade": 6 }
```
