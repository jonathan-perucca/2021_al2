package com.example.demo.security.login;

import com.example.demo.security.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

public class JwtAuthorizationFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    public JwtAuthorizationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        var token = resolveToken(httpRequest);
        if (hasText(token) && tokenProvider.validateToken(token)) {
            var authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        var bearerPart = "Bearer ";
        var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (hasText(authorization) && authorization.startsWith(bearerPart)) {
            return authorization.substring(bearerPart.length());
        }

        return null;
    }
}
