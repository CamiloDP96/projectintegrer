package com.projecti.projectintegrer.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projecti.projectintegrer.auth.TokenAuthentication;
import com.projecti.projectintegrer.exception.TokenExpiredException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);

            try {
                Claims claims = jwtUtil.extractAndVerifyClaims(jwt);
                String username = claims.getSubject();

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    TokenAuthentication tokenAuthentication = new TokenAuthentication(jwt, username);
                    SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
                }
            } catch (ExpiredJwtException e) {
                logger.error("Token expired: {}");
                throw new TokenExpiredException();
            }
        }
        filterChain.doFilter(request, response);
    }

}
