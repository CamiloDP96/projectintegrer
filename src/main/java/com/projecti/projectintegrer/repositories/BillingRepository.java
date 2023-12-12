package com.projecti.projectintegrer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecti.projectintegrer.domain.entities.Billing;

public interface BillingRepository extends JpaRepository<Billing, Integer>{
}
