package com.emmanuel.banksecurity.config;

import com.emmanuel.banksecurity.exception.JwtAuthenticationEntryPoint;
import com.emmanuel.banksecurity.filter.JwtAuthenticationFilter;
import com.emmanuel.banksecurity.jwt.JwtService;
import com.emmanuel.banksecurity.properties.BankSecurityProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Configuração automática da biblioteca Bank Security.
 * <p>
 * Esta classe é responsável por registrar automaticamente
 * todos os Beans necessários para o funcionamento da biblioteca
 * de segurança quando ela for adicionada como dependência em
 * outro projeto Spring Boot.
 * <p>
 * Também habilita o carregamento das propriedades definidas
 * no application.yml através da classe BankSecurityProperties.
 * <p>
 * Exemplo:
 * <p>
 * bank:
 * security:
 * secret: minha-chave-secreta
 * expiration: 86400000
 */
@AutoConfiguration
@EnableConfigurationProperties(BankSecurityProperties.class)
public class BankSecurityAutoConfiguration {

    @Bean
    JwtService jwtService(BankSecurityProperties bankSecurityProperties) {
        return new JwtService(bankSecurityProperties);
    }


    @Bean
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtService jwtService) {

        return new JwtAuthenticationFilter(jwtService);

    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint)
            throws Exception {

        return http

                // Desabilita o CSRF, pois a autenticação será realizada
                // através de JWT enviado no Header Authorization.
                .csrf(csrf -> csrf.disable())

                // Desabilita a tela de login padrão do Spring Security,
                // pois a autenticação será realizada pelo endpoint
                // /auth/login utilizando JWT.
                .formLogin(form -> form.disable())

                // Desabilita a autenticação HTTP Basic,
                // pois a API utiliza JWT para autenticação.
                .httpBasic(basic -> basic.disable())

                // Define que a aplicação não manterá sessões HTTP.
                // Cada requisição será autenticada utilizando
                // o JWT enviado no Header Authorization.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )


                // Usa nosso EntryPoint para responder 401
                // quando não existir autenticação válida.
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(jwtAuthenticationEntryPoint) )

                // Define endpoints públicos e protegidos.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // Executa nosso filtro JWT antes do filtro
                // padrão de usuário e senha do Spring.
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }
}