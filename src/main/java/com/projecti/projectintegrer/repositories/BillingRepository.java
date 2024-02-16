package com.projecti.projectintegrer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projecti.projectintegrer.domain.entities.Billing;

public interface BillingRepository extends JpaRepository<Billing, Integer>{

    Page<Billing> findAll(Pageable pageable);
}
