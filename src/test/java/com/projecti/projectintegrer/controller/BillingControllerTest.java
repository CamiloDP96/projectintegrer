package com.projecti.projectintegrer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecti.projectintegrer.domain.dto.BillDetailDto;
import com.projecti.projectintegrer.domain.dto.BillingDto;
import com.projecti.projectintegrer.service.BillDetailService;
import com.projecti.projectintegrer.service.BillingService;

public class BillingControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BillingService billingService;

    @InjectMocks
    private BillingController billingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(billingController).build();
    }

    @Test
    void testRegisterBill() throws Exception {
        // Mock request data
        BillingDto billingDto = new BillingDto(null, null/* Add necessary parameters */);

        mockMvc.perform(post("/api/v1/Bill/createBill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(billingDto)))
                .andExpect(status().isOk());

        // Verify if billingService.createBilling was called
        verify(billingService, times(1)).createBilling(billingDto);
    }

    @Test
    void testShowBills() throws Exception {
        // Mocking behavior of billingService.getAllBillings()
        when(billingService.getAllBillings()).thenReturn(null/* Mocked billing list */);

        mockMvc.perform(get("/api/v1/Bill/billsList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify if billingService.getAllBillings was called
        verify(billingService, times(1)).getAllBillings();
    }

    @Test
    void testDeleteBill() throws Exception {
        Integer billIdToDelete = 1; // Replace with an actual ID

        mockMvc.perform(delete("/api/v1/Bill/deleteBill/{id}", billIdToDelete))
                .andExpect(status().isNoContent());

        // Verify if billingService.deleteBilling was called with the provided ID
        verify(billingService, times(1)).deleteBilling(billIdToDelete);
    }

    @Test
    void testUpdateBill() throws Exception {
        // Mock request data
        BillingDto billingDto = new BillingDto(/* Add necessary parameters */);

        mockMvc.perform(put("/api/v1/Bill/update/bill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(billingDto)))
                .andExpect(status().isNoContent());

        // Verify if billingService.updateBilling was called
        verify(billingService, times(1)).updateBilling(billingDto);
    }

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
