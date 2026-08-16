# Customer Service

Microsserviço responsável pelo ciclo de vida dos clientes do Bank Microservices.

> [!NOTE]
> Este módulo faz parte de um projeto em evolução. A API e a persistência de clientes estão implementadas, mas integrações distribuídas e uma cobertura homogênea de testes ainda estão no roadmap.

## Funcionalidades atuais

- criação, consulta e listagem paginada de clientes;
- atualização de dados cadastrais;
- ativação e desativação;
- validações e tratamento padronizado de erros;
- PostgreSQL, Flyway, proteção JWT e OpenAPI/Swagger;
- testes unitários, de controller, repositório e integração.

## Endpoints

| Método | Rota | Descrição |
| --- | --- | --- |
| `POST` | `/customers` | Cadastra um cliente |
| `GET` | `/customers/{id}` | Consulta por ID |
| `GET` | `/customers` | Lista com paginação |
| `PUT` | `/customers/{id}` | Atualiza os dados permitidos |
| `PATCH` | `/customers/{id}/activate` | Ativa o cliente |
| `PATCH` | `/customers/{id}/deactivate` | Desativa o cliente |

## Execução

Na raiz do repositório:

```bash
docker compose up --build
```

API: `http://localhost:8080/customers`

Swagger: `http://localhost:8080/swagger-ui/index.html`

Para executar apenas o módulo, use PostgreSQL em `localhost:5433`, instale antes `bank-security` no Maven local e rode `./mvnw spring-boot:run`.

## Configuração

| Variável | Padrão local |
| --- | --- |
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5433/customerdb` |
| `SPRING_DATASOURCE_USERNAME` | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` |
| `BANK_SECURITY_SECRET` | sem padrão |
| `BANK_SECURITY_EXPIRATION` | `86400000` |

## Testes e roadmap

Execute `./mvnw test`; o relatório JaCoCo é gerado em `target/site/jacoco/`.

Próximas evoluções: integração com os demais serviços, testes de contrato, mensageria, observabilidade e CI/CD.
