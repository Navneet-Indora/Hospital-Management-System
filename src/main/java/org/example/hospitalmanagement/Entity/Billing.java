package org.example.hospitalmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.PaymentMethod;
import org.example.hospitalmanagement.Entity.Enums.PaymentStatus;

import java.time.LocalDate;

@Entity
@Table(name="billing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relation
    @OneToOne
    @JoinColumn(name="appointment_id",nullable = false)   //nullable false in join column is putting a check at database level means without appointmnet there can not be any billing
    private Appointment appointment;

    //Invoice
    @Column(nullable = false,unique = true)
    private String invoiceNumber;    //INV-2024-001

    @Column(nullable = false)
    private LocalDate billingDate;

    private LocalDate dueDate; // paymnent due date

    @Column(nullable = false)
    private Double consultationFees;   //doctor fees

    @Builder.Default
    private Double taxAmount=0.0;    //Tax

    @Builder.Default
    private Double discountAmount=0.0; // discount

    @Column(nullable = false)
    private Double totalAmount; //final Amount

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private PaymentStatus paymentStatus=PaymentStatus.UNPAID;
    //paid,unpaid, pending

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // CAsh, Card , Online

    private LocalDate paymentDate;

    @Column(length=1000)
    private String notes;
}
