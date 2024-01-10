package com.projecti.projectintegrer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projecti.projectintegrer.domain.dto.BillDetailDto;
import com.projecti.projectintegrer.domain.entities.BillDetail;
import com.projecti.projectintegrer.repositories.BillingDetailRepository;
import com.projecti.projectintegrer.service.BillDetailService;

public class BillDetailServiceTest {
    @Mock
    private BillingDetailRepository billDetailRepository;

    @InjectMocks
    private BillDetailService billDetailService;

    @Test
    void testCreateBillDetail() {
        BillDetailDto billDetailDto = new BillDetailDto(/* Add necessary parameters */);
        BillDetail billDetail = BillDetail.builder()
                .description(billDetailDto.description())
                .amount(billDetailDto.amount())
                .build();

        billDetailService.createBillDetail(billDetailDto);

        verify(billDetailRepository, times(1)).save(billDetail);
    }

    @Test
    void testUpdateBillDetail() {
        BillDetailDto billDetailDto = new BillDetailDto(/* Add necessary parameters */);
        BillDetail billDetail = BillDetail.builder()
                .id(billDetailDto.id())
                .description(billDetailDto.description())
                .amount(billDetailDto.amount())
                .build();

        when(billDetailRepository.findById(billDetailDto.id())).thenReturn(Optional.of(billDetail));

        billDetailService.updateBillDetail(billDetailDto);

        verify(billDetailRepository, times(1)).save(billDetail);
    }

    @Test
    void testDeleteBillDetail() {
        Integer billDetailId = 1; // Replace with an actual ID
        BillDetail billDetail = BillDetail.builder().build();

        when(billDetailRepository.findById(billDetailId)).thenReturn(Optional.of(billDetail));

        billDetailService.deletebillDetail(billDetailId);

        verify(billDetailRepository, times(1)).delete(billDetail);
    }

    @Test
    void testGetAllBillDetails() {
        List<BillDetail> billDetailList = new ArrayList<>(); // Replace with a list of mock data

        when(billDetailRepository.findAll()).thenReturn(billDetailList);

        List<BillDetail> result = billDetailService.getAllbillDetails();

        assertEquals(billDetailList.size(), result.size());
    }

    @Test
    void testGetBillDetailById() {
        Integer billDetailId = 1; // Replace with an actual ID
        BillDetail billDetail = BillDetail.builder().build();

        when(billDetailRepository.findById(billDetailId)).thenReturn(Optional.of(billDetail));

        Optional<BillDetail> result = billDetailService.getClientById(billDetailId);

        assertTrue(result.isPresent());
        assertEquals(billDetail, result.get());
    }
}
