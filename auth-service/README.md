# 🔐 Auth Service

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-green)
![JWT](https://img.shields.io/badge/JWT-Authentication-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)
![Maven](https://img.shields.io/badge/Maven-3.9+-red)

Microsserviço responsável pela autenticação dos usuários da plataforma bancária.

O **Auth Service** centraliza o processo de autenticação da arquitetura, realizando cadastro de usuários, validação de credenciais e emissão de tokens JWT consumidos pelos demais microsserviços.

---

# 📚 Índice

- Sobre
- Arquitetura
- Tecnologias
- Funcionalidades
- Estrutura
- Fluxo de Autenticação
- Configuração
- Docker
- Endpoints
- Segurança
- Testes
- Roadmap

---

# 📖 Sobre

O Auth Service é responsável por autenticar usuários da plataforma e emitir um **Access Token JWT**.

Após autenticado, o cliente utiliza esse token para acessar os demais microsserviços protegidos:

- Customer Service
- Account Service
- Transaction Service

Toda configuração compartilhada de segurança é fornecida pela biblioteca **bank-security**.

---

# 🏗 Arquitetura

```text
                    Cliente
                       │
                       │
             POST /auth/login
                       │
                       ▼
               AuthController
                       │
                       ▼
               AuthRestMapper
                       │
                       ▼
                 LoginCommand
                       │
                       ▼
                  AuthService
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
AuthenticationManager PasswordEncoder AuthUserRepository
      │                                 │
      │                                 ▼
      │                          PostgreSQL
      │
      ▼
AuthUserDetailsService
      │
      ▼
      JWT
      │
      ▼
 LoginResult
      │
      ▼
LoginResponse
      │
      ▼
 Cliente
```

---

# 📂 Estrutura do Projeto

```text
src
└── main
    ├── java
    │
    └── com.emmanuel.authservice
        │
        ├── auth
        │   ├── application
        │   │   ├── command
        │   │   └── result
        │   │
        │   ├── controller
        │   │
        │   ├── domain
        │   │   ├── entity
        │   │   └── enums
        │   │
        │   ├── dto
        │   │   ├── request
        │   │   └── response
        │   │
        │   ├── mapper
        │   │
        │   ├── repository
        │   │
        │   ├── security
        │   │   ├── AuthUserDetails
        │   │   └── AuthUserDetailsService
        │   │
        │   └── service
        │
        └── config
```

---

# 🚀 Tecnologias

- Java 21
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Flyway
- Docker
- Docker Compose
- Spring Data JPA
- Maven

---

# ✅ Funcionalidades

- Cadastro de usuários
- Login por e-mail e senha
- Geração de Access Token JWT
- Criptografia de senha
- Autenticação Stateless
- Integração com Spring Security
- Integração com PostgreSQL
- Versionamento do banco com Flyway
- Docker Compose
- Integração com a biblioteca bank-security

---

# 🔐 Fluxo de Autenticação

```text
Cliente

        │

        ▼

POST /auth/login

        │

        ▼

LoginRequest

        │

        ▼

LoginCommand

        │

        ▼

AuthService

        │

        ▼

AuthenticationManager

        │

        ▼

AuthUserDetailsService

        │

        ▼

AuthUserRepository

        │

        ▼

PostgreSQL

        │

        ▼

JWT

        │

        ▼

LoginResult

        │

        ▼

LoginResponse

        │

        ▼

Access Token
```

---

# ⚙ Configuração

```yaml
server:
  port: 8083

spring:
  application:
    name: auth-service

  datasource:
    url: jdbc:postgresql://localhost:5435/authdb
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: validate

  flyway:
    enabled: true

security:
  jwt:
    secret: ${JWT_SECRET}
    expiration: 86400000
```

---

# 🌱 Variáveis de Ambiente

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-auth:5432/authdb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
JWT_SECRET=your-secret-key
```

---

# 🐳 Docker

Executar os containers:

```bash
docker compose up --build
```

Executar em segundo plano:

```bash
docker compose up -d --build
```

Parar os containers:

```bash
docker compose down
```

Logs:

```bash
docker compose logs -f auth-service
```

---

# 📡 Endpoints

## Cadastro

```http
POST /auth/register
```

Request

```json
{
  "name": "Emmanuel Gomes",
  "email": "gomes@email.com",
  "password": "123456"
}
```

Response

```json
{
  "id": "9dc312a4-d30d-46fc-a623-1ff7308f2300",
  "name": "Emmanuel Gomes",
  "email": "gomes@email.com"
}
```

---

## Login

```http
POST /auth/login
```

Request

```json
{
  "email": "gomes@email.com",
  "password": "123456"
}
```

Response

```json
{
  "accessToken": "eyJhbGciOiJIUzM4NCJ9...",
  "tokenType": "Bearer"
}
```

---

## Requisição autenticada

```http
GET /customers
Authorization: Bearer eyJhbGciOiJIUzM4NCJ9...
```

---

# 🔓 Endpoints Públicos

```text
/auth/**
```

Todos os demais endpoints exigem um token JWT válido.

---

# 🔒 Segurança

O serviço utiliza autenticação **Stateless**.

```java
SessionCreationPolicy.STATELESS
```

As senhas são criptografadas utilizando:

```java
PasswordEncoder
```

A autenticação é realizada por:

- AuthenticationManager
- UserDetailsService
- JWT
- Spring Security

---

# 🧪 Testes

Executar todos os testes:

```bash
./mvnw test
```

Windows

```bash
mvnw.cmd test
```

Build completo

```bash
./mvnw clean verify
```

---

# 📈 Benefícios

- Segurança centralizada
- JWT
- Arquitetura Stateless
- Microsserviços desacoplados
- Reutilização da biblioteca bank-security
- Fácil manutenção
- Escalabilidade
- Código padronizado

---

# 🔗 Projetos Relacionados

| Projeto | Responsabilidade |
|----------|------------------|
| auth-service | Autenticação e emissão de JWT |
| bank-security | Biblioteca compartilhada de segurança |
| customer-service | Gestão de clientes |
| account-service | Gestão de contas |
| transaction-service | Processamento de transações |

---

# 🛣 Roadmap

### ✅ Implementado

- Cadastro de usuários
- Login
- JWT
- PasswordEncoder
- UserDetailsService
- PostgreSQL
- Docker Compose
- Flyway
- Integração com bank-security

### 🚧 Próximos passos

- Refresh Token
- Roles
- Permissions
- Method Security
- Blacklist de Tokens
- Auditoria
- Recuperação de senha
- Confirmação de e-mail
- Testes de integração
- OpenAPI/Swagger

---

# 📄 Licença

Projeto desenvolvido para fins de estudo, portfólio e evolução em arquitetura de microsserviços utilizando Java e Spring Boot.

---

# 👨‍💻 Autor

**Emmanuel Gomes**

Backend Java Developer

Projeto desenvolvido utilizando Java, Spring Boot, Spring Security, JWT, PostgreSQL e arquitetura baseada em microsserviços.