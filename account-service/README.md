# Account Service

Microsserviço responsável por criar e gerenciar contas e seus saldos no Bank Microservices.

> [!NOTE]
> Este módulo está em evolução. As operações locais de conta estão implementadas, mas ainda não há validação remota do cliente nem integração real com o `transaction-service`.

## Funcionalidades atuais

- criação de conta com agência padrão e número gerado por sequência;
- consulta por ID e listagem paginada;
- bloqueio, desbloqueio e encerramento;
- consulta e atualização direta de saldo;
- regras de domínio, tratamento de erros, PostgreSQL, Flyway, JWT e Swagger.

## Endpoints

| Método | Rota | Descrição |
| --- | --- | --- |
| `POST` | `/accounts` | Cria uma conta |
| `GET` | `/accounts/{id}` | Consulta por ID |
| `GET` | `/accounts` | Lista com paginação |
| `PATCH` | `/accounts/{id}/block` | Bloqueia a conta |
| `PATCH` | `/accounts/{id}/unblock` | Reativa a conta |
| `PATCH` | `/accounts/{id}/close` | Encerra a conta |
| `GET` | `/accounts/{id}/balance` | Consulta o saldo |
| `PATCH` | `/accounts/{id}/balance` | Atualiza o saldo diretamente |

## Execução

Na raiz: `docker compose up --build`.

API: `http://localhost:8081/accounts`

Swagger: `http://localhost:8081/swagger-ui/index.html`

Execução isolada: PostgreSQL em `localhost:5434`, `bank-security` instalado localmente e `./mvnw spring-boot:run`.

## Configuração

| Variável | Padrão local |
| --- | --- |
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5434/accountdb` |
| `SPRING_DATASOURCE_USERNAME` | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` |
| `BANK_SECURITY_SECRET` | sem padrão |
| `BANK_SECURITY_EXPIRATION` | `86400000` |

## Testes e roadmap

Execute `./mvnw test`. A suíte ainda é inicial.

Próximas evoluções: validar clientes remotamente, restringir atualização de saldo ao fluxo transacional, integrar transações e ampliar testes, observabilidade e CI/CD.
