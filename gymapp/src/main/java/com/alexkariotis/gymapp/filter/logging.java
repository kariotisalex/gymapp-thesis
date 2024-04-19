package com.alexkariotis.gymapp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Component
@WebFilter(filterName = "CustomFilter", urlPatterns = "/")
@Slf4j
public class logging extends OncePerRequestFilter {

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
        LocalDateTime start = LocalDateTime.now();


        try {
            filterChain.doFilter(request, response);
            log.info("Request {} {} - Succeed - {}ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
        } catch (Exception e) {
            log.error("Request {} {} - Failed - {}ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
            throw e;
        }

    }
}
