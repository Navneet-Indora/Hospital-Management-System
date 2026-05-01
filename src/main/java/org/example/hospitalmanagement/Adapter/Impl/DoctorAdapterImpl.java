package org.example.hospitalmanagement.Adapter.Impl;

import org.example.hospitalmanagement.Adapter.DoctorAdapter;
import org.example.hospitalmanagement.Entity.Doctor;
import org.example.hospitalmanagement.dto.request.DoctorRequestDto;
import org.example.hospitalmanagement.dto.response.DoctorResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DoctorAdapterImpl implements DoctorAdapter {
    @Override
    public Doctor toEntity(DoctorRequestDto doctorRequestDto) {
        return Doctor.builder()
                .name(doctorRequestDto.getName())
                .email(doctorRequestDto.getEmail())
                .phone(doctorRequestDto.getPhone())
                .address(doctorRequestDto.getAddress())
                .gender(doctorRequestDto.getGender())
                .specialization(doctorRequestDto.getSpecialization())
                .qualification(doctorRequestDto.getQualification())
                .experienceYears(doctorRequestDto.getExperienceYears())
                .consultationFees(doctorRequestDto.getConsultationFees())
                .availableDays(doctorRequestDto.getAvailableDays())
                .availableTime(doctorRequestDto.getAvailableTime())
                .build();
    }

    @Override
    public DoctorResponseDto toResponseDto(Doctor doctor) {
        return DoctorResponseDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .address(doctor.getAddress())
                .gender(doctor.getGender())
                .specialization(doctor.getSpecialization())
                .qualification(doctor.getQualification())
                .experienceYears(doctor.getExperienceYears())
                .consultationFees(doctor.getConsultationFees())
                .availableDays(doctor.getAvailableDays())
                .availableTime(doctor.getAvailableTime())
                .status(doctor.getStatus())
                .build();
    }
}
