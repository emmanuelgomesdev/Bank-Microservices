# Transaction Service

Backend desenvolvido em **Java 21** utilizando **Spring Boot 3**, com foco em boas práticas, arquitetura limpa e microsserviços.

Este projeto faz parte da solução **Banking Microservices**, desenvolvida para simular uma arquitetura bancária moderna semelhante às utilizadas por instituições financeiras. O objetivo é evoluir gradualmente o projeto até uma arquitetura distribuída, incluindo comunicação entre microsserviços, mensageria, observabilidade e deploy em nuvem.

---

## 🏦 Banking Microservices

Atualmente a solução é composta pelos seguintes microsserviços:

- ✅ customer-service
- ✅ account-service
- ✅ transaction-service

Próximas evoluções:

- Comunicação entre microsserviços
- Kafka
- Redis
- AWS
- CI/CD
- Observabilidade

---

## 🚀 Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker
- Docker Compose
- Maven
- JUnit 5
- Mockito
- Swagger / OpenAPI

---

## 📁 Arquitetura

O projeto segue organização por feature.

```
transaction
│
├── application
├── controller
├── domain
├── dto
├── mapper
├── repository
├── service
└── validation
```

Documentações técnicas encontram-se na pasta **docs/**.

---

## ✨ Funcionalidades

- Criação de transações
- Consulta de transação por ID
- Listagem paginada de transações
- Validação de regras de negócio
- Validação de valor da transação
- Cálculo de saldo da operação
- Tratamento global de exceções
- Documentação OpenAPI

---

## ▶️ Executando o projeto

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
```

### 2. Subir o banco de dados

```bash
docker compose up
```

### 3. Executar a aplicação

Execute a classe:

```
TransactionServiceApplication
```

ou execute pelo IntelliJ IDEA.

---

## 📚 Documentação da API

Após iniciar a aplicação:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🗄️ Banco de Dados

As alterações do banco são versionadas utilizando **Flyway**.

Todas as migrations encontram-se em:

```
src/main/resources/db/migration
```

---

## 🧪 Testes

Linux/macOS

```bash
./mvnw test
```

Windows

```powershell
mvnw.cmd test
```

---

## 🐳 Docker

Subir ambiente

```bash
docker compose up
```

Parar ambiente

```bash
docker compose down
```

---

## 📌 Roadmap

- [x] Criação de transações
- [x] Consulta por ID
- [x] Listagem paginada
- [x] Validação de regras de negócio
- [x] Tratamento global de exceções
- [x] Docker
- [x] Docker Compose
- [x] Flyway
- [ ] Integração com Account Service
- [ ] Integração com Customer Service
- [ ] Comunicação entre Microsserviços
- [ ] Kafka
- [ ] Redis
- [ ] AWS
- [ ] GitHub Actions
- [ ] CI/CD
- [ ] Observabilidade
- [ ] Testes de Integração

---

## 👨‍💻 Autor

**Emmanuel Gomes**