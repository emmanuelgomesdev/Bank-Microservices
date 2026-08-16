# Transaction Service

Microsserviço responsável por registrar operações de crédito e débito.

> [!WARNING]
> **Implementação parcial e em evolução.** A persistência e as regras básicas existem, mas a consulta de conta, seu status e saldo ainda é simulada no código. Este módulo não representa um fluxo financeiro real.

## O que está implementado

- criação de créditos e débitos;
- validação de valor e saldo para débito;
- cálculo do saldo resultante;
- consulta por ID e listagem paginada;
- PostgreSQL, Flyway, tratamento de erros, Swagger e Actuator.

## Mock atual

Enquanto a comunicação com o `account-service` não é implementada, o serviço:

- reconhece apenas a conta `8ad2a0c9-1989-4b89-9728-83ccd96ee18d`;
- considera essa conta como `ACTIVE`;
- usa saldo inicial fixo de `100` em cada operação;
- registra o saldo calculado na transação, sem alterar a conta real.

Esse comportamento temporário permite evoluir o domínio de forma incremental.

## Endpoints

| Método | Rota | Descrição |
| --- | --- | --- |
| `POST` | `/transactions` | Registra uma transação |
| `GET` | `/transactions/{id}` | Consulta por ID |
| `GET` | `/transactions` | Lista com paginação |

## Execução

Na raiz: `docker compose up --build`.

API: `http://localhost:8082/transactions`

Swagger: `http://localhost:8082/swagger-ui/index.html`

Execução isolada: PostgreSQL em `localhost:5436` e `./mvnw spring-boot:run`.

## Testes e roadmap

Execute `./mvnw test`. Atualmente existe apenas o teste de contexto.

Próximas evoluções: remover os mocks, integrar contas, garantir idempotência e consistência, implementar Kafka, adicionar segurança compartilhada e ampliar testes, observabilidade e CI/CD.
