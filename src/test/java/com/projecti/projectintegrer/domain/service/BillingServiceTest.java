package com.projecti.projectintegrer.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projecti.projectintegrer.domain.dto.BillingDto;
import com.projecti.projectintegrer.domain.entities.Billing;
import com.projecti.projectintegrer.repositories.BillingRepository;
import com.projecti.projectintegrer.service.BillingService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BillingServiceTest {

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingService billingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createBillingTest() {
        BillingDto billingDto = new BillingDto(null, 100.0);

        billingService.createBilling(billingDto);

        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    void updateBillingTest() {
        BillingDto billingDto = new BillingDto(1, 200.0);
        Billing mockBilling = new Billing();

        when(billingRepository.findById(anyInt())).thenReturn(Optional.of(mockBilling));

        assertDoesNotThrow(() -> billingService.updateBilling(billingDto));

        verify(billingRepository, times(1)).findById(anyInt());
        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    void deleteBillingTest() {
        when(billingRepository.findById(anyInt())).thenReturn(Optional.of(new Billing()));

        assertDoesNotThrow(() -> billingService.deleteBilling(1));

        verify(billingRepository, times(1)).delete(any(Billing.class));
    }

    @Test
    void getAllBillingsTest() {
        Billing mockBilling = new Billing();
        when(billingRepository.findAll()).thenReturn(Collections.singletonList(mockBilling));

        assertEquals(Collections.singletonList(mockBilling), billingService.getAllBillings());
    }

    @Test
    void getBillingByIdTest() {
        Billing mockBilling = new Billing();
        when(billingRepository.findById(anyInt())).thenReturn(Optional.of(mockBilling));

        assertEquals(Optional.of(mockBilling), billingService.getClientById(1));
    }
}
