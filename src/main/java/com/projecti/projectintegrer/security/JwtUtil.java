package com.projecti.projectintegrer.security;

import org.springframework.stereotype.Component;

import com.projecti.projectintegrer.auth.TokenDto;
import com.projecti.projectintegrer.domain.entities.UserRoleEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.List;

import static com.projecti.projectintegrer.utilities.Constants.CLAIMS_ROLES_KEY;


@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public TokenDto generateToken(String name, List<UserRoleEnum> roles) {

        Date expirationDate = jwtConfig.getExpirationDate();
        String token = Jwts.builder().subject(name)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .claim(CLAIMS_ROLES_KEY, roles)
                .signWith(jwtConfig.getSigningKey())
                .compact();
        return new TokenDto(token, expirationDate);
    }

    public Claims extractAndVerifyClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}

