package com.projecti.projectintegrer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecti.projectintegrer.domain.dto.BillingDto;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.service.BillingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/Bill")
public record BillingController(
    BillingService billingService
) {
    @PostMapping("/createBill")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> registerBill(@RequestBody BillingDto billingDto) throws ReservException{
        billingService.createBilling(billingDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("billsList/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> showBills(
        @PathVariable("offset") Integer offset,
        @PathVariable("limit") Integer limit) throws ReservException{
            List<BillingDto> bills = billingService.getAllBillings(offset, limit);
        return new ResponseEntity<>(bills,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findBillById(@PathVariable("id") Integer id) throws ReservException {
        BillingDto bills = billingService.getBillById(id);
        return new ResponseEntity<>(bills, HttpStatus.FOUND);
    }


    @DeleteMapping("deleteBill/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteBill(@PathVariable("id") Integer id) throws ReservException {
        billingService.deleteBilling(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("updateBill/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateBill(@PathVariable("id") Integer id, @RequestBody BillingDto billingDto) throws ReservException{
        billingService.updateBilling(id, billingDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
