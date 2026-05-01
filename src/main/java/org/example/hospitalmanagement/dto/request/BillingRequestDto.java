package org.example.hospitalmanagement.dto.request;

import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.PaymentMethod;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingRequestDto {
    //client sends appointment id only
    private Long appointmentId;

    //Amount Details
    private Double consultationFees;   //doctor fees
    private Double taxAmount;
    private Double discountAmount; // discount

    //payment
    private LocalDate dueDate;
    private PaymentMethod paymentMethod;
    private String notes;
}
