package org.example.hospitalmanagement.Adapter;

import org.example.hospitalmanagement.Entity.Appointment;
import org.example.hospitalmanagement.dto.request.AppointmentRequestDto;
import org.example.hospitalmanagement.dto.response.AppointmentResponseDto;

public interface AppointmentAdapter {
    Appointment toEntity(AppointmentRequestDto appointmentRequestDto);

    AppointmentResponseDto toResponseDto(Appointment appointment);
}
