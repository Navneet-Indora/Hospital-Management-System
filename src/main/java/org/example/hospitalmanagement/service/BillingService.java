package org.example.hospitalmanagement.service;

import org.example.hospitalmanagement.Adapter.BillingAdapter;
import org.example.hospitalmanagement.Entity.Billing;
import org.example.hospitalmanagement.Entity.Enums.PaymentMethod;
import org.example.hospitalmanagement.Entity.Enums.PaymentStatus;
import org.example.hospitalmanagement.dto.request.BillingRequestDto;
import org.example.hospitalmanagement.dto.response.BillingResponseDto;
import org.example.hospitalmanagement.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillingService {
    private final BillingAdapter billingAdapter;
    private final BillingRepository billingRepository;

    public BillingService(BillingAdapter billingAdapter, BillingRepository billingRepository) {
        this.billingAdapter = billingAdapter;
        this.billingRepository = billingRepository;
    }

    //Create Billing
    public BillingResponseDto createBilling(BillingRequestDto billingRequestDto){
        //convert dto to entity
        Billing billing=billingAdapter.toEntity(billingRequestDto);
        //save to db
        Billing savedBilling=billingRepository.save(billing);
        //convert to ResponseDto and return
        return billingAdapter.toResponseDto(savedBilling);
    }

    //Get All Billings
    public List<BillingResponseDto> getAllBilling(){
        //fetch all billing from db
        List<Billing>billings=billingRepository.findAll();
        //convert each billing to ResponseDto
        List<BillingResponseDto> billingResponseDtos=new ArrayList<>();

        for(Billing billing:billings){
            BillingResponseDto billingResponseDto=billingAdapter.toResponseDto(billing);
            billingResponseDtos.add(billingResponseDto);
        }
        return billingResponseDtos;
    }

    //get billing by id
    public BillingResponseDto getBillingById(Long id){
        Optional<Billing> billingOptional=billingRepository.findById(id);
        //check if billing exist
        if(!billingOptional.isPresent()){
            throw new RuntimeException("Billing not found with Id: "+id);
        }
        Billing billing=billingOptional.get();
        //convert to responsedto and return
        return billingAdapter.toResponseDto(billing);
    }

    //Get billing by appointment
    public BillingResponseDto getBillingByAppointment(Long appointmentId){
        //fetch billing by appointment id
        Optional<Billing> billingOptional=billingRepository.findByAppointmentId(appointmentId);
        //check if billing exist
        if(!billingOptional.isPresent()){
            throw new RuntimeException("Billing not found with Appointment Id: "+appointmentId);
        }
        Billing billing=billingOptional.get();
        //convert to responsedto and return
        return billingAdapter.toResponseDto(billing);
    }

    //Mark Payment as Paid
    public BillingResponseDto markAsPaid(Long id, PaymentMethod paymentMethod){
        //fetch billing by  id
        Optional<Billing> billingOptional=billingRepository.findById(id);
        //check if billing exist
        if(!billingOptional.isPresent()){
            throw new RuntimeException("Billing not found with Appointment Id: "+id);
        }
        Billing billing=billingOptional.get();
        //update payment details
        billing.setPaymentStatus(PaymentStatus.PAID);
        billing.setPaymentDate(LocalDate.now());
        billing.setPaymentMethod(paymentMethod);
        //save
        Billing updatedBilling=billingRepository.save(billing);
        //convert to responseDto and return
        return billingAdapter.toResponseDto(updatedBilling);
    }

    //Mark Payment as Pending
    public BillingResponseDto markAsPending(Long id, PaymentMethod paymentMethod){
        //fetch billing by  id
        Optional<Billing> billingOptional=billingRepository.findById(id);
        //check if billing exist
        if(!billingOptional.isPresent()){
            throw new RuntimeException("Billing not found with Appointment Id: "+id);
        }
        Billing billing=billingOptional.get();
        //update payment details
        billing.setPaymentStatus(PaymentStatus.PENDING);
        billing.setPaymentDate(LocalDate.now());
        billing.setPaymentMethod(paymentMethod);
        //save
        Billing updatedBilling=billingRepository.save(billing);
        //convert to responseDto and return
        return billingAdapter.toResponseDto(updatedBilling);
    }

    //Get All unpaid Billings
    public List<BillingResponseDto> getUnpaidBilling(){
        //fetch all unpaid Billings
        List<Billing> billings=billingRepository.findByPaymentStatus(PaymentStatus.UNPAID);
        //convert each to ResponseDto
        List<BillingResponseDto> billingResponseDtos=new ArrayList<>();

        for(Billing billing:billings){
            BillingResponseDto billingResponseDto=billingAdapter.toResponseDto(billing);
            billingResponseDtos.add(billingResponseDto);
        }
        return billingResponseDtos;
    }
    //Get All Paid Billings
    public List<BillingResponseDto> getPaidBillings(){
        //fetch all unpaid Billings
        List<Billing> billings=billingRepository.findByPaymentStatus(PaymentStatus.PAID);
        //convert each to ResponseDto
        List<BillingResponseDto> billingResponseDtos=new ArrayList<>();

        for(Billing billing:billings){
            BillingResponseDto billingResponseDto=billingAdapter.toResponseDto(billing);
            billingResponseDtos.add(billingResponseDto);
        }
        return billingResponseDtos;
    }


}
