package com.projecti.projectintegrer.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecti.projectintegrer.domain.entities.Client;
import com.projecti.projectintegrer.exception.InvalidCredentialsException;
import com.projecti.projectintegrer.security.JwtUtil;
import com.projecti.projectintegrer.service.ClientService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ClientService clientService;

    private final JwtUtil jwtUtil;


    public AuthController(ClientService clientService, JwtUtil jwtUtil) {
        this.clientService = clientService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        // 1. Find user by username
        String clientName = loginDto.getClient();
        Optional<Client> clientOptional = clientService.findByName(clientName);

        // 2. Validate user existence and password
        if (clientOptional.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        Client client = clientOptional.get();
        String passwordHash = client.getPasswordHash();
        if (!BCrypt.checkpw(loginDto.getPassword(), passwordHash)) {
            throw new InvalidCredentialsException();
        }

        // 3. Generate JWT token
        TokenDto tokenDto = jwtUtil.generateToken(clientName, client.getRoles());
        // 4. Return successful response with token
        return ResponseEntity.ok(tokenDto);
    }


}
