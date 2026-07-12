# 🛡️ Bank Security

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-green)
![JWT](https://img.shields.io/badge/JWT-Authentication-blue)
![Maven](https://img.shields.io/badge/Maven-3.9+-red)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

Biblioteca responsável por centralizar toda a configuração de autenticação e autorização dos microsserviços da plataforma.

O principal objetivo é eliminar duplicação de código, padronizar a segurança entre os serviços e facilitar a manutenção da arquitetura.

---

# 📚 Índice

- Sobre
- Arquitetura
- Tecnologias
- Funcionalidades
- Estrutura do Projeto
- Como Utilizar
- Configuração
- Fluxo de Autenticação
- Exemplo de Requisição
- Compatibilidade
- Roadmap
- Autor

---

# 📖 Sobre

Em arquiteturas baseadas em microsserviços, normalmente diversos serviços precisam implementar exatamente a mesma configuração de segurança.

Sem uma biblioteca compartilhada, seria necessário copiar:

- SecurityFilterChain
- JWT Filter
- PasswordEncoder
- AuthenticationManager
- UserDetailsService
- Exception Handler
- Configurações do Spring Security

para cada microsserviço.

Esta biblioteca centraliza toda essa responsabilidade em um único projeto.

Assim, qualquer alteração de segurança é realizada apenas nesta biblioteca.

---

# 🏗 Arquitetura

```

                +----------------+
                | Auth Service   |
                +--------+-------+
|
JWT
|
v
+----------------------+
|   Bank Security Lib  |
|----------------------|
| JWT Filter           |
| Security Config      |
| Password Encoder     |
| UserDetailsService   |
| Authentication       |
+----------+-----------+
|
|
+----------+----------+
|                     |
v                     v

Customer Service      Account Service

|
v

Transaction Service

```

Todos os microsserviços compartilham exatamente a mesma implementação de segurança.

---

# 🚀 Tecnologias

- Java 21
- Spring Boot
- Spring Security
- JWT
- Maven

---

# ✅ Funcionalidades

- Auto Configuration
- SecurityFilterChain
- JWT Authentication
- Stateless Session
- PasswordEncoder
- AuthenticationManager
- UserDetailsService
- Authorization Filter
- Endpoints públicos
- Configuração reutilizável
- Integração transparente

---

# 📂 Estrutura

```

src
└── main
├── java
│
└── com.emmanuel.banksecurity
│
├── config
├── filter
├── jwt
├── service
├── exception
├── util
│
└── resources
└── META-INF
└── spring
└── org.springframework.boot.autoconfigure.AutoConfiguration.imports

```

---

# 📦 Instalação

Adicionar a dependência Maven.

```xml
<dependency>
    <groupId>com.emmanuel</groupId>
    <artifactId>bank-security</artifactId>
    <version>1.0.0</version>
</dependency>
```

Após adicionar a dependência, toda configuração será registrada automaticamente.

Não é necessário criar:

- SecurityConfig
- PasswordEncoder
- JWT Filter
- AuthenticationManager

em cada microsserviço.

---

# ⚙ Configuração

Exemplo de configuração.

```yaml
security:
  jwt:
    secret: your-secret-key
    expiration: 86400000
```

---

# 🔐 Fluxo de Autenticação

```

Cliente

|
| Authorization: Bearer Token
|
v

JWT Filter

|
v

Validação do Token

|
v

Spring Security

|
v

Controller

|
v

Service

```

Caso o token seja inválido:

```

401 Unauthorized

```

---

# 🌐 Endpoints Públicos

Por padrão:

```
/auth/**
```

Todos os demais endpoints exigem autenticação.

---

# 📨 Exemplo

### Login

```
POST /auth/login
```

Resposta:

```json
{
  "accessToken": "eyJhbGciOiJIUzM4NCJ9...",
  "tokenType": "Bearer"
}
```

---

### Requisição autenticada

```
GET /customers
```

Header:

```
Authorization: Bearer eyJhbGc...
```

---

# 🔄 Fluxo de Utilização

```

Cliente

↓

Auth Service

↓

JWT

↓

Customer Service

↓

Account Service

↓

Transaction Service

```

Todos utilizam exatamente a mesma biblioteca de segurança.

---

# 📈 Benefícios

- Centralização da segurança
- Reutilização de código
- Padronização entre microsserviços
- Facilidade de manutenção
- Menor acoplamento
- Configuração automática
- Evolução simplificada
- Maior produtividade

---

# 💻 Compatibilidade

| Tecnologia | Versão |
|------------|---------|
| Java | 21 |
| Spring Boot | 4.x |
| Spring Security | 6.x |
| Maven | 3.9+ |

---

# 🛣 Roadmap

### ✅ Implementado

- SecurityFilterChain
- PasswordEncoder
- JWT Filter
- AuthenticationManager
- AutoConfiguration

### 🚧 Próximos passos

- Refresh Token
- Roles
- Permissions
- Method Security
- OAuth2
- OpenID Connect
- Multi Tenant
- Auditoria
- Blacklist de Tokens
- Token Rotation

---

# 📄 Licença

Projeto desenvolvido para fins de estudo e evolução em arquitetura de microsserviços.

---

# 👨‍💻 Autor

**Emmanuel Gomes**

Backend Java Developer

Projeto desenvolvido utilizando Java, Spring Boot, Spring Security e arquitetura baseada em microsserviços.