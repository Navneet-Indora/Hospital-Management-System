package org.example.hospitalmanagement.dto.response;

import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.PaymentMethod;
import org.example.hospitalmanagement.Entity.Enums.PaymentStatus;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingResponseDto {
    private Long id;
    //Appointment Info
    private Long appointmentId;
    private String patientName;
    private String doctorName;

    //Invoice
    private String invoiceNumber;
    private LocalDate billingDate;
    private LocalDate dueDate;

    //Amount
    private Double consultationFees;
    private Double taxAmount;
    private Double discountAmount;
    private Double totalAmount;

    //Payment
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;
    private String notes;
}

