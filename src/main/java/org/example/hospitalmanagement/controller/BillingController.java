package org.example.hospitalmanagement.controller;

import org.example.hospitalmanagement.Entity.Enums.PaymentMethod;
import org.example.hospitalmanagement.dto.request.BillingRequestDto;
import org.example.hospitalmanagement.dto.response.BillingResponseDto;
import org.example.hospitalmanagement.service.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {
    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    //create a Billing
    //Post/api/v1/billing
    @PostMapping
    public ResponseEntity<BillingResponseDto> createBilling(@RequestBody BillingRequestDto billingRequestDto){
        BillingResponseDto response= billingService.createBilling(billingRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get All Billings
    @GetMapping
    public ResponseEntity<List<BillingResponseDto>> getAllBillings(){
        List<BillingResponseDto> response=billingService.getAllBilling();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Billing By id
    @GetMapping("/{id}")
    public ResponseEntity<BillingResponseDto> getBillingById(@PathVariable Long id){
        BillingResponseDto response=billingService.getBillingById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Billing by appointment id
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<BillingResponseDto> getBillingByAppointment(@PathVariable Long appointmentId){
        BillingResponseDto response=billingService.getBillingByAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Mark as Paid
    @PutMapping("/{id}/pay")
    public ResponseEntity<BillingResponseDto> markAsPaid(@PathVariable Long id, @RequestParam PaymentMethod paymentMethod){
        BillingResponseDto response=billingService.markAsPaid(id,paymentMethod);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Mark as Pending
    @PutMapping("/{id}/pending")
    public ResponseEntity<BillingResponseDto> markAsPending(@PathVariable Long id,@RequestParam PaymentMethod paymentMethod){
        BillingResponseDto response=billingService.markAsPending(id,paymentMethod);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get All unpaid Billings
    @GetMapping("/unpaid")
    public ResponseEntity<List<BillingResponseDto>> getUnpaidBillings(){
        List<BillingResponseDto> response=billingService.getUnpaidBilling();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get All paid Billings
    @GetMapping("/paid")
    public ResponseEntity<List<BillingResponseDto>> getPaidBillings(){
        List<BillingResponseDto> response=billingService.getPaidBillings();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
