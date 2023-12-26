package com.projecti.projectintegrer.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.entities.Client;
import com.projecti.projectintegrer.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public JwtUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> optionalUser = clientRepository.findByName(username);
        if (optionalUser.isPresent()) {
            Client client = optionalUser.get();
            List<SimpleGrantedAuthority> authorities = client.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .toList();
            return new User(client.getName(), client.getPasswordHash(), authorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
