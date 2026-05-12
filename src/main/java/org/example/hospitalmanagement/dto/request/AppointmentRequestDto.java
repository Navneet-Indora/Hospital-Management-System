package org.example.hospitalmanagement.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message="Patient ID is required")
    private Long patientId;

    @NotNull(message="Doctor id is required")
    private Long doctorId;

    @NotNull(message = "Appointment date is required")
    @Future(message="Appointment date must be in future")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    @NotBlank(message = "Reason is required")
    private String reason;   //reason for visit
}

