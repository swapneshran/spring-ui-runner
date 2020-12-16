package com.cbl.spring.ui.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class SpringAppForHostingUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAppForHostingUiApplication.class, args);
    }

    @Bean
    public OncePerRequestFilter angularForwardFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                String mode = request.getHeader("Sec-Fetch-Mode");
                RequestDispatcher rd = request.getRequestDispatcher("/");
                if (mode != null && mode.equals("navigate")) { // angular navigation
                    rd.forward(request, response);
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        };
    }

}
