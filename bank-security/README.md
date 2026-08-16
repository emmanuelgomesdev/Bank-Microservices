# Bank Security

Biblioteca interna que reúne a configuração JWT compartilhada pelos serviços Spring Boot.

> [!NOTE]
> Biblioteca em evolução, criada para estudo e reutilização neste monorepo. Ainda não é publicada em um repositório Maven nem representa uma solução completa de segurança para produção.

## Responsabilidades atuais

- auto-configuração do Spring Security;
- política stateless e filtro `Bearer`;
- geração e validação de JWT com JJWT;
- resposta `401 Unauthorized` para autenticação inválida;
- liberação de `/auth/**`, `/v3/api-docs/**` e `/swagger-ui/**`;
- leitura de `bank.security.secret` e `bank.security.expiration`.

Qualquer outra rota é protegida por padrão.

## Uso no monorepo

Instale a biblioteca no Maven local:

```bash
./mvnw -f bank-security/pom.xml install
```

Dependência usada pelos consumidores:

```xml
<dependency>
    <groupId>com.emmanuel</groupId>
    <artifactId>bank-security</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Configuração:

```yaml
bank:
  security:
    secret: ${BANK_SECURITY_SECRET}
    expiration: ${BANK_SECURITY_EXPIRATION:86400000}
```

Use uma chave com pelo menos 32 bytes e nunca versione segredos reais.

## Limitações e roadmap

- rotas públicas ainda são fixas;
- não há autorização granular, refresh token, revogação ou rotação de chave;
- distribuição e versionamento ainda são locais;
- a suíte de testes é inicial.

Próximas evoluções: configuração de rotas por serviço, testes do JWT/filtro, autorização por papéis e estratégia de rotação e publicação.
