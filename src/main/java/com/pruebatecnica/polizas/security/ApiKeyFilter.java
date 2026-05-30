package com.pruebatecnica.polizas.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    private static final String API_KEY = "123456";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Permitir Swagger y OpenAPI
        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")) {

            chain.doFilter(request, response);
            return;
        }

        String apiKey = req.getHeader("x-api-key");

        if (!API_KEY.equals(apiKey)) {

            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("API Key invalida");
            return;
        }

        chain.doFilter(request, response);
    }
}