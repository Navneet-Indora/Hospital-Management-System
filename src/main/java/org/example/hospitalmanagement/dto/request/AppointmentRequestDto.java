package org.example.hospitalmanagement.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {
    //Client needs Ids only not full objects
    private Long patientId;
    private Long doctorId;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    private String reason;   //reason for visit
}

