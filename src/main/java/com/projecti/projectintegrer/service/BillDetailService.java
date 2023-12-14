package com.projecti.projectintegrer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.BillDetailDto;
import com.projecti.projectintegrer.domain.entities.BillDetail;
import com.projecti.projectintegrer.repositories.BillingDetailRepository;

@Service
public record BillDetailService(BillingDetailRepository billDetailRepository) {
     // Create
    public void createBillDetail(BillDetailDto billDetailDto){
        BillDetail billDetail = BillDetail.builder()
            .description(billDetailDto.description())
            .amount(billDetailDto.amount())
            .build();

        billDetailRepository.save(billDetail);
    }

    // Update
    public void updateBillDetail(BillDetailDto billDetailDto){
        BillDetail billDetail = billDetailRepository.findById(billDetailDto.id())
            .orElseThrow(() -> new RuntimeException("BillDetail not found..."));
        updateBillDetailData(billDetail, billDetailDto);
        billDetailRepository.save(billDetail);
    }

    private void updateBillDetailData(
        BillDetail billDetail,
        BillDetailDto billDetailDto
        ){
            billDetail.setId(billDetailDto.id());
            billDetail.setDescription(billDetailDto.description());
            billDetail.setAmount(billDetailDto.amount());
        }

        // Delete
        public void deletebillDetail(Integer billDetailId){
            BillDetail billDetail = billDetailRepository.findById(billDetailId)
                .orElseThrow(()-> new RuntimeException("BillDetail not found"));
            billDetailRepository.delete(billDetail);
        }

        public List<BillDetail> getAllbillDetails(){
            return billDetailRepository.findAll();
        }
        public Optional<BillDetail> getClientById (Integer billDetailId){
            return billDetailRepository.findById(billDetailId);
        }
}

