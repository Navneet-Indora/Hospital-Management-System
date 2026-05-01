package org.example.hospitalmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relations
    @ManyToOne
    @JoinColumn(name="patient_id",nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id",nullable = false)
    private Doctor doctor;

    //Schedule
    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private LocalTime appointmentTime;

    //Details
    @Column(nullable=false)
    private String reason;   //reason for visit

    @Column(length=1000)
    private String notes;    //doctor notes after visit

    //Status
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    @Builder.Default
    private AppointmentStatus appointmentStatus=AppointmentStatus.SCHEDULED;
    //Scheduled, COmpleted , Cacncelled, Rescheduled

    //Reschedule Tracking
    private LocalDate previousDate;// old date if rescheduled
    private LocalTime previousTime; // old Time if reschduled

    //Notification flags
    @Builder.Default
    private Boolean patientNotified=false;

    @Builder.Default
    private Boolean doctorNotified=false;
}
