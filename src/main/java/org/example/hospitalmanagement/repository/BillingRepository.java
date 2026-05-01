package org.example.hospitalmanagement.repository;

import org.example.hospitalmanagement.Entity.Billing;
import org.example.hospitalmanagement.Entity.Enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing,Long> {
    //Get Billing By Appointment
    Optional<Billing> findByAppointmentId(Long appointmentId);
    //Get Billing By payment Status
    List<Billing> findByPaymentStatus(PaymentStatus paymentStatus);
}
