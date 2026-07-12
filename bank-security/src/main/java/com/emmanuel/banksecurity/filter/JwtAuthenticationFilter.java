package com.emmanuel.banksecurity.filter;

import com.emmanuel.banksecurity.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filtro responsável por interceptar todas as requisições HTTP.
 * Para cada requisição:
 * 1. Verifica se existe o Header Authorization.
 * 2. Extrai o JWT.
 * 3. Valida o token utilizando a JwtService.
 * 4. Caso esteja válido, autentica o usuário no Spring Security.
 * A classe estende OncePerRequestFilter para garantir que
 * o filtro seja executado apenas uma única vez durante
 * cada requisição HTTP.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Remove o prefixo "Bearer" e mantém apenas o JWT.
        String jwt = authHeader.substring(7);

        // Extrai o usuário armazenado no Subject do JWT.
        String userName = jwtService.extractUsername(jwt);

        // Continua a autenticação somente quando o usuário foi
        // encontrado no token e ainda não existe autenticação registrada.
        if (userName != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Confirma se o JWT ainda está válido.
            if (jwtService.isTokenValid(jwt)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userName,
                                null,
                                List.of()
                        );

                // Adiciona detalhes técnicos da requisição.
                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // Registra o usuário autenticado.
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        // Continua a requisição até o Controller.
        filterChain.doFilter(request, response);
    }
}