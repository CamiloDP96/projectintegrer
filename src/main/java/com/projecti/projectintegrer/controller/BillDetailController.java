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

import com.projecti.projectintegrer.domain.dto.BillDetailDto;
import com.projecti.projectintegrer.service.BillDetailService;

@RestController
@RequestMapping("api/v1/billDetails")
public record BillDetailController(
    BillDetailService billDetailService
) {
    @PostMapping("/createBillDetails")
    public ResponseEntity<?> registerBill(@RequestBody BillDetailDto billDetailDto){
        billDetailService.createBillDetail(billDetailDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("billDetailsList")
    public ResponseEntity<?> showBillDetails(){
        return new ResponseEntity<>(billDetailService.getAllbillDetails(),HttpStatus.OK);
    }

    @DeleteMapping("deleteBillDetail/{id}")
    public ResponseEntity<?> deleteBillDetail(@PathVariable("id") Integer id){
        billDetailService.deletebillDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/billDetails")
        public ResponseEntity<?> updateBillDetails(@RequestBody BillDetailDto billDetailDto){
            billDetailService.updateBillDetail(billDetailDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
