package com.projecti.projectintegrer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projecti.projectintegrer.domain.entities.BillDetail;


public interface BillingDetailRepository extends MongoRepository<BillDetail, Integer>{
}
