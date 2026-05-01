package org.example.hospitalmanagement.Adapter.Impl;

import org.example.hospitalmanagement.Adapter.PatientAdapter;
import org.example.hospitalmanagement.Entity.Patient;
import org.example.hospitalmanagement.dto.request.PatientRequestDto;
import org.example.hospitalmanagement.dto.response.PatientResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class PatientAdapterImpl implements PatientAdapter {

    @Override
    public Patient toEntity(PatientRequestDto patientRequestDto) {
        Integer age=null;
        if(patientRequestDto.getDateOfBirth() != null){
            age= Period.between(patientRequestDto.getDateOfBirth(), LocalDate.now()).getYears();
        }
        return Patient.builder()
                .name(patientRequestDto.getName())
                .email(patientRequestDto.getEmail())
                .phone(patientRequestDto.getPhone())
                .address(patientRequestDto.getAddress())
                .gender(patientRequestDto.getGender())
                .dateOfBirth(patientRequestDto.getDateOfBirth())
                .age(age)
                .bloodGroup(patientRequestDto.getBloodGroup())
                .allergies(patientRequestDto.getAllergies())
                .medicalHistory(patientRequestDto.getMedicalHistory())
                .currentTreatment(patientRequestDto.getCurrentTreatment())
                .build();
    }

    @Override
    public PatientResponseDto toResponseDto(Patient patient) {
        return PatientResponseDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .address(patient.getAddress())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .age(patient.getAge())
                .bloodGroup(patient.getBloodGroup())
                .allergies(patient.getAllergies())
                .medicalHistory(patient.getMedicalHistory())
                .currentTreatment(patient.getCurrentTreatment())
                .status(patient.getStatus())
                .build();

    }
}
