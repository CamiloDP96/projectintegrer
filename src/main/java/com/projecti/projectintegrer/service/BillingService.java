package com.projecti.projectintegrer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.BillingDto;
import com.projecti.projectintegrer.domain.entities.Billing;
import com.projecti.projectintegrer.exception.MessageEnum;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.mapper.BillingMapper;
import com.projecti.projectintegrer.repositories.BillingRepository;

@Service
public record BillingService(
    BillingRepository billingRepository,
    BillingMapper mapper
    ) {
     // Create
    public void createBilling(BillingDto billingDto){
        Billing billing = mapper.toEntity(billingDto);
        billingRepository.save(billing);
    }

    // Update
    public void updateBilling(Integer Id,BillingDto billingDto) throws ReservException {
        billingRepository.findById(Id)
            .orElseThrow(() -> new ReservException(MessageEnum.DATA_NOT_FOUND));
        Billing billing = mapper.toEntity(billingDto);
        billingRepository.save(billing);
    }

        // Delete
        public void deleteBilling(Integer Id){
            Billing billing = billingRepository.findById(Id)
                .orElseThrow(()-> new RuntimeException("Billing not found"));
            billingRepository.delete(billing);
        }

        //getter
        public List<BillingDto> getAllBillings(Integer offset, Integer limit) throws ReservException {
            Pageable pageable = PageRequest.of(offset, limit);
            Page<Billing> billing = billingRepository.findAll(pageable);
            if (billing.getContent().isEmpty()) {
                throw new ReservException(MessageEnum.DATA_NOT_FOUND);
            }
            return mapper.toDtoList(billing.getContent());
        }

        public BillingDto getBillById (Integer Id) throws ReservException {
        Billing billing = billingRepository.findById(Id)
            .orElseThrow(() -> new ReservException(MessageEnum.DATA_NOT_FOUND));
        return mapper.toDto(billing);
        }
}

