package com.emmanuel.banksecurity.jwt;

import com.emmanuel.banksecurity.properties.BankSecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtService {

    private final BankSecurityProperties bankSecurityProperties;

    public JwtService(BankSecurityProperties bankSecurityProperties) {
        this.bankSecurityProperties = bankSecurityProperties;
    }

    /**
     * Responsável por criar um JWT para um usuário.
     * Fluxo:
     *
     * 1. Inicia a construção do token (builder).
     * 2. Define quem é o dono do token (subject).
     * 3. Registra a data e hora da criação (issuedAt).
     * 4. Calcula a data de expiração utilizando o horário atual
     * mais o tempo configurado na BankSecurityProperties.
     * 5. Assina o JWT utilizando a SecretKey.
     * 6. Compacta todas as informações (Header + Payload + Signature)
     * em uma única String pronta para ser enviada ao cliente.
     */
    public String generateToken(String userName) {

        Date issuedAt = new Date();

        Date expiration = new Date(
                System.currentTimeMillis()
                        + bankSecurityProperties.getExpiration()
        );

        return Jwts.builder()
                .subject(userName)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(getSignKey())
                .compact();
    }

    /**
     * Responsável por transformar a chave secreta
     * definida no application.yml em uma SecretKey.
     *
     * Fluxo:
     * 1. Obtém a chave secreta da BankSecurityProperties.
     * 2. Converte a String em bytes.
     * 3. A classe Keys transforma os bytes em uma SecretKey.
     * 4. A SecretKey será utilizada para assinar e validar o JWT.
     */
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(
                bankSecurityProperties
                        .getSecret()
                        .getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * Responsável por extrair o usuário (Subject)
     * armazenado dentro do JWT.
     *
     * Fluxo:
     * 1. Abre o token.
     * 2. Recupera todos os Claims.
     * 3. Retorna apenas o Subject do token.
     */
    public String extractUsername(String token) {
        return extractAllClaims(token)
                .getSubject();
    }

    /**
     * Responsável por abrir o JWT e recuperar todos os
     * Claims armazenados dentro dele.
     *
     * Fluxo:
     * 1. Inicia o parser da biblioteca JJWT.
     * 2. Informa qual SecretKey será utilizada para validar o token.
     * 3. Constrói o parser.
     * 4. Abre e valida o JWT recebido.
     * 5. Retorna todos os Claims armazenados no Payload.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Responsável por verificar se o JWT ainda é válido.
     *
     * Fluxo:
     * 1. Verifica se o token expirou.
     * 2. Se não expirou, retorna true.
     * 3. Se expirou, retorna false.
     */
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    /**
     * Responsável por verificar se o JWT já expirou.
     *
     * Fluxo:
     * 1. Abre o token e recupera todos os Claims.
     * 2. Obtém a data de expiração gravada no JWT.
     * 3. Compara essa data com a data e hora atual.
     * 4. Retorna true se o token já venceu.
     */
    private boolean isTokenExpired(String token) {

        Date expiration = extractAllClaims(token)
                .getExpiration();

        return expiration == null
                || expiration.before(new Date());
    }

}