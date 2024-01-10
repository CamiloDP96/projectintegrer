package com.projecti.projectintegrer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projecti.projectintegrer.domain.dto.BillDetailDto;
import com.projecti.projectintegrer.service.BillDetailService;

public class BillDetailControllerTest {

    @Mock
    private BillDetailService billDetailService;

    @InjectMocks
    private BillDetailController billDetailController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test cases for each method in the controller
    // Test case for createBillDetails method
    @Test
    void testCreateBillDetails() {
        BillDetailDto billDetailDto = new BillDetailDto(null, null, null/* Add necessary parameters */);

        // Mock behavior of billDetailService.createBillDetail method
        doNothing().when(billDetailService).createBillDetail(billDetailDto);

        ResponseEntity<?> responseEntity = billDetailController.registerBill(billDetailDto);

        // Verify if the method was called once and returned HttpStatus.OK
        verify(billDetailService, times(1)).createBillDetail(billDetailDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
