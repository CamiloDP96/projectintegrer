package com.projecti.projectintegrer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecti.projectintegrer.domain.entities.BillDetail;


public interface BillingDetailRepository extends JpaRepository<BillDetail, Integer>{
}
