package org.example.hospitalmanagement.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.aspectj.bridge.IMessage;
import org.example.hospitalmanagement.Entity.Enums.PaymentMethod;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingRequestDto {
    //client sends appointment id only
    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;

    //Amount Details
    @NotNull(message = "Consultation fees is required")
    @Min(value=0, message="Consultation fees cannot be negative")
    private Double consultationFees;   //doctor fees

    @Min(value=0, message="Tax amount cannot be negative")
    private Double taxAmount;

    @Min(value = 0, message = "Discount amount cannot be negative")
    private Double discountAmount; // discount

    //payment
    private LocalDate dueDate;
    private PaymentMethod paymentMethod;
    private String notes;
}
