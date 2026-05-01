package org.example.hospitalmanagement.Adapter;

import org.example.hospitalmanagement.Entity.Doctor;
import org.example.hospitalmanagement.dto.request.DoctorRequestDto;
import org.example.hospitalmanagement.dto.response.DoctorResponseDto;

public interface DoctorAdapter{
     Doctor toEntity(DoctorRequestDto doctorRequestDto);

     DoctorResponseDto toResponseDto(Doctor doctor);
}
