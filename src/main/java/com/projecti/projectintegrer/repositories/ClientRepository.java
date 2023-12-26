package com.projecti.projectintegrer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projecti.projectintegrer.domain.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    Optional<Client> findByName(String name);
}
