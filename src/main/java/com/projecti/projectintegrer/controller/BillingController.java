package com.projecti.projectintegrer.controller;

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
import com.projecti.projectintegrer.service.BillingService;

@RestController
@RequestMapping("api/v1/Bill")
public record BillingController(
    BillingService billingService
) {
    @PostMapping("/createBill")
    public ResponseEntity<?> registerBill(@RequestBody BillingDto billingDto){
        billingService.createBilling(billingDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("billsList")
    public ResponseEntity<?> showBills(){
        return new ResponseEntity<>(billingService.getAllBillings(),HttpStatus.OK);
    }

    @DeleteMapping("deleteBill/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable("id") Integer id){
        billingService.deleteBilling(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/bill")
        public ResponseEntity<?> updateBill(@RequestBody BillingDto billingDto){
            billingService.updateBilling(billingDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
