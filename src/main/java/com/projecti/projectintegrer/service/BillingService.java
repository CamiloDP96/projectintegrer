package com.projecti.projectintegrer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.BillingDto;
import com.projecti.projectintegrer.domain.entities.Billing;
import com.projecti.projectintegrer.repositories.BillingRepository;

@Service
public record BillingService(BillingRepository billingRepository) {
     // Create
    public void createBilling(BillingDto billingDto){
        Billing billing = Billing.builder()
            .total_amount(billingDto.total_amount())
            .build();

        billingRepository.save(billing);
    }

    // Update
    public void updateBilling(BillingDto billingDto){
        Billing billing = billingRepository.findById(billingDto.id())
            .orElseThrow(() -> new RuntimeException("Billing not found..."));
        updateBillingData(billing, billingDto);
        billingRepository.save(billing);
    }

    private void updateBillingData(
        Billing billing,
        BillingDto billingDto
        ){
            billing.setId(billingDto.id());
            billing.setTotal_amount(billingDto.total_amount());
        }

        // Delete
        public void deleteBilling(Integer billingId){
            Billing billing = billingRepository.findById(billingId)
                .orElseThrow(()-> new RuntimeException("Billing not found"));
            billingRepository.delete(billing);
        }

        public List<Billing> getAllBillings(){
            return billingRepository.findAll();
        }
        public Optional<Billing> getClientById (Integer billingId){
            return billingRepository.findById(billingId);
        }
}

