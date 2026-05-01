package org.example.hospitalmanagement.Adapter.Impl;

import org.example.hospitalmanagement.Adapter.BillingAdapter;
import org.example.hospitalmanagement.Entity.Appointment;
import org.example.hospitalmanagement.Entity.Billing;
import org.example.hospitalmanagement.dto.request.BillingRequestDto;
import org.example.hospitalmanagement.dto.response.BillingResponseDto;
import org.example.hospitalmanagement.repository.AppointmentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BillingAdapterImplementation implements BillingAdapter {
    private final AppointmentRepository appointmentRepository;

    public BillingAdapterImplementation(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    public Billing toEntity(BillingRequestDto billingRequestDto) {

        Appointment appointment= appointmentRepository.findById(billingRequestDto.getAppointmentId())
                .orElseThrow(()->new RuntimeException("Appointment not found by id"+billingRequestDto.getAppointmentId()));

        //Calculate Total Amount
        Double constultationFees=billingRequestDto.getConsultationFees();
        Double taxAmount= billingRequestDto.getTaxAmount() != null ?
                billingRequestDto.getTaxAmount() : 0.0;
        Double discountAmount= billingRequestDto.getDiscountAmount() != null ?
                billingRequestDto.getDiscountAmount() : 0.0;
        Double totalAmount=constultationFees+taxAmount-discountAmount;

        //Generate Invoice
        String invoiceNumber="INV-" + LocalDate.now().getYear()
                +"-"+billingRequestDto.getAppointmentId();
        return Billing.builder()
                .appointment(appointment)
                .consultationFees(constultationFees)
                .taxAmount(taxAmount)
                .discountAmount(discountAmount)
                .totalAmount(totalAmount)
                .invoiceNumber(invoiceNumber)
                .billingDate(LocalDate.now())
                .dueDate(billingRequestDto.getDueDate())
                .paymentMethod(billingRequestDto.getPaymentMethod())
                .notes(billingRequestDto.getNotes())
                .build();
    }

    @Override
    public BillingResponseDto toResponseDto(Billing billing) {
        return BillingResponseDto.builder()
                .id(billing.getId())
                .appointmentId(billing.getAppointment().getId())
                .patientName(billing.getAppointment().getPatient().getName())
                .doctorName(billing.getAppointment().getDoctor().getName())
                .invoiceNumber(billing.getInvoiceNumber())
                .billingDate(billing.getBillingDate())
                .dueDate(billing.getDueDate())
                .consultationFees(billing.getConsultationFees())
                .taxAmount(billing.getTaxAmount())
                .discountAmount(billing.getDiscountAmount())
                .totalAmount(billing.getTotalAmount())
                .paymentStatus(billing.getPaymentStatus())
                .paymentMethod(billing.getPaymentMethod())
                .paymentDate(billing.getPaymentDate())
                .notes(billing.getNotes())
                .build();
    }
}
