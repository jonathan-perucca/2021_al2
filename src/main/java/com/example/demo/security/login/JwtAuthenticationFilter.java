package com.example.demo.security.login;

import com.example.demo.security.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final TokenProvider tokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        super(authenticationManager);
        super.setFilterProcessesUrl("/login");
        this.tokenProvider = tokenProvider;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            var loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            ));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadCredentialsException("Could not authenticate user");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        var token = tokenProvider.createToken(authResult);
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        response.getWriter().flush();
    }
}
