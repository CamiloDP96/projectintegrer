package com.projecti.projectintegrer.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projecti.projectintegrer.domain.entities.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

    Page<Client> findAll(Pageable pageable);

    Optional<Client> findByUsername(String username);
}
