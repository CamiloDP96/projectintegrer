package com.projecti.projectintegrer.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projecti.projectintegrer.domain.dto.BillDetailDto;
import com.projecti.projectintegrer.domain.entities.BillDetail;
import com.projecti.projectintegrer.repositories.BillingDetailRepository;
import com.projecti.projectintegrer.service.BillDetailService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class BillDetailServiceTest {

    @Mock
    private BillingDetailRepository billDetailRepository;

    @InjectMocks
    private BillDetailService billDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createBillDetailTest() {
        BillDetailDto billDetailDto = new BillDetailDto(null, "Test Description", 100.0);

        billDetailService.createBillDetail(billDetailDto);

        verify(billDetailRepository, times(1)).save(any(BillDetail.class));
    }

    @Test
    void updateBillDetailTest() {
        BillDetailDto billDetailDto = new BillDetailDto(1, "Updated Description", 200.0);
        BillDetail mockBillDetail = new BillDetail();

        when(billDetailRepository.findById(anyInt())).thenReturn(Optional.of(mockBillDetail));

        assertDoesNotThrow(() -> billDetailService.updateBillDetail(billDetailDto));

        verify(billDetailRepository, times(1)).findById(anyInt());
        verify(billDetailRepository, times(1)).save(any(BillDetail.class));
    }

    @Test
    void deleteBillDetailTest() {
        BillDetail mockBillDetail = new BillDetail();

        when(billDetailRepository.findById(anyInt())).thenReturn(Optional.of(mockBillDetail));

        assertDoesNotThrow(() -> billDetailService.deletebillDetail(1));

        verify(billDetailRepository, times(1)).delete(any(BillDetail.class));
    }

    @Test
    void getAllBillDetailsTest() {
        BillDetail mockBillDetail = new BillDetail();
        when(billDetailRepository.findAll()).thenReturn(Collections.singletonList(mockBillDetail));

        assertEquals(Collections.singletonList(mockBillDetail), billDetailService.getAllbillDetails());
    }

    @Test
    void getClientByIdTest() {
        BillDetail mockBillDetail = new BillDetail();
        when(billDetailRepository.findById(anyInt())).thenReturn(Optional.of(mockBillDetail));

        assertEquals(Optional.of(mockBillDetail), billDetailService.getClientById(1));
    }
}
