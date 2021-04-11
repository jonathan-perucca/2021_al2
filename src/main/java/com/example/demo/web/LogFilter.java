package com.example.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class LogFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        log.info("Log filter triggered for {} - {}", req.getMethod(), req.getRequestURI());
        // ici pre dispatcher servlet

        chain.doFilter(request, response);

        // ici post dispatcher servlet
    }
}
