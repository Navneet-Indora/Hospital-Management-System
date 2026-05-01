package org.example.hospitalmanagement.dto.response;

import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentResponseDto {

    private Long id;

    private Long patientId;
    private String patientName;

    private Long doctorId;
    private String doctorName;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    private String reason;   //reason for visit
    private String notes;
    private AppointmentStatus appointmentStatus;
    private Boolean patientNotified;
    private Boolean doctorNotified;

}
