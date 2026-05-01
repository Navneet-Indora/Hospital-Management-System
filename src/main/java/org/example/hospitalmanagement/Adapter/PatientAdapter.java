package org.example.hospitalmanagement.Adapter;

import org.example.hospitalmanagement.Entity.Patient;
import org.example.hospitalmanagement.dto.request.PatientRequestDto;
import org.example.hospitalmanagement.dto.response.PatientResponseDto;

public interface PatientAdapter {
    //RequestDto -> Entity (before saving to db)
    Patient toEntity(PatientRequestDto patientRequestDto);
    //Entity -> ResponseDto (befire sending to client)
    PatientResponseDto toResponseDto(Patient patient);
}
