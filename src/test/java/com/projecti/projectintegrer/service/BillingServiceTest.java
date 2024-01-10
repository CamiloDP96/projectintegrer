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
import com.projecti.projectintegrer.domain.dto.BillingDto;
import com.projecti.projectintegrer.domain.entities.Billing;
import com.projecti.projectintegrer.repositories.BillingRepository;
import com.projecti.projectintegrer.service.BillDetailService;

public class BillingServiceTest {
    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingService billingService;

    @Test
    void testCreateBilling() {
        BillingDto billingDto = new BillingDto(/* Add necessary parameters */);
        Billing billing = Billing.builder()
                .total_amount(billingDto.total_amount())
                .build();

        billingService.createBilling(billingDto);

        verify(billingRepository, times(1)).save(billing);
    }

    @Test
    void testUpdateBilling() {
        BillingDto billingDto = new BillingDto(/* Add necessary parameters */);
        Billing billing = Billing.builder()
                .id(billingDto.id())
                .total_amount(billingDto.total_amount())
                .build();

        when(billingRepository.findById(billingDto.id())).thenReturn(Optional.of(billing));

        billingService.updateBilling(billingDto);

        verify(billingRepository, times(1)).save(billing);
    }

    @Test
    void testDeleteBilling() {
        Integer billingId = 1; // Replace with an actual ID
        Billing billing = Billing.builder().build();

        when(billingRepository.findById(billingId)).thenReturn(Optional.of(billing));

        billingService.deleteBilling(billingId);

        verify(billingRepository, times(1)).delete(billing);
    }

    @Test
    void testGetAllBillings() {
        List<Billing> billingList = new ArrayList<>(); // Replace with a list of mock data

        when(billingRepository.findAll()).thenReturn(billingList);

        List<Billing> result = billingService.getAllBillings();

        assertEquals(billingList.size(), result.size());
    }

    @Test
    void testGetBillingById() {
        Integer billingId = 1; // Replace with an actual ID
        Billing billing = Billing.builder().build();

        when(billingRepository.findById(billingId)).thenReturn(Optional.of(billing));

        Optional<Billing> result = billingService.getClientById(billingId);

        assertTrue(result.isPresent());
        assertEquals(billing, result.get());
    }
}
