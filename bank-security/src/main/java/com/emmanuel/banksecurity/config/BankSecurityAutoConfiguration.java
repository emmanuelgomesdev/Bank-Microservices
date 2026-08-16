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
 *
 * Esta classe é responsável por registrar automaticamente
 * todos os Beans necessários para o funcionamento da biblioteca
 * de segurança quando ela for adicionada como dependência em
 * outro projeto Spring Boot.
 *
 * Também habilita o carregamento das propriedades definidas
 * no application.yml através da classe BankSecurityProperties.
 *
 * Exemplo:
 *
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

                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(jwtAuthenticationEntryPoint) )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }
}