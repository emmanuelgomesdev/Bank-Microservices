package com.emmanuel.banksecurity.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;


/**
 * Responsável por responder requisições que tentam acessar
 * endpoints protegidos sem uma autenticação válida.
 *
 * Quando o Spring Security identifica que o usuário não está
 * autenticado, esta classe retorna o status HTTP 401 Unauthorized.
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {

        // Retorna 401 quando o usuário não está autenticado.
        response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
    }
}
