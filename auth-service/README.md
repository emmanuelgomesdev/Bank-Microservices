# Auth Service

Microsserviço responsável pelo cadastro das credenciais, autenticação e emissão de tokens JWT.

> [!NOTE]
> Este é um fluxo básico de autenticação para um projeto em evolução. Refresh token, revogação, recuperação de senha e autorização granular ainda não estão implementados.

## Funcionalidades atuais

- cadastro por e-mail e senha;
- hash de senha com `PasswordEncoder`;
- autenticação com Spring Security;
- emissão de access token JWT;
- PostgreSQL, Flyway e biblioteca `bank-security`.

## Endpoints públicos

| Método | Rota | Descrição |
| --- | --- | --- |
| `POST` | `/auth/register` | Cadastra credenciais |
| `POST` | `/auth/login` | Retorna um JWT |

Use o token nas APIs protegidas: `Authorization: Bearer <jwt>`.

## Execução

Na raiz: `docker compose up --build`. O serviço fica em `http://localhost:8083/auth`.

Execução isolada: PostgreSQL em `localhost:5435`, `bank-security` instalado no Maven local e `./mvnw spring-boot:run`.

## Configuração

| Variável | Padrão local | Finalidade |
| --- | --- | --- |
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5435/authdb` | Conexão PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | `postgres` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` | Senha do banco |
| `BANK_SECURITY_SECRET` | sem padrão | Chave HMAC (use pelo menos 32 bytes) |
| `BANK_SECURITY_EXPIRATION` | `86400000` | Validade em milissegundos |

## Testes e roadmap

Execute `./mvnw test`. A suíte ainda é inicial.

Próximas evoluções: erros padronizados, refresh token, revogação, papéis/permissões, testes, observabilidade e CI/CD.
