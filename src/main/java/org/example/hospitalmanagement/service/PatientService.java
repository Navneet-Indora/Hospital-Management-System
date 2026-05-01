package org.example.hospitalmanagement.service;

import org.example.hospitalmanagement.Adapter.PatientAdapter;
import org.example.hospitalmanagement.Entity.Enums.Status;
import org.example.hospitalmanagement.Entity.Patient;
import org.example.hospitalmanagement.dto.request.PatientRequestDto;
import org.example.hospitalmanagement.dto.response.PatientResponseDto;
import org.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientAdapter patientAdapter;

    public PatientService(PatientRepository patientRepository, PatientAdapter patientAdapter) {
        this.patientRepository = patientRepository;
        this.patientAdapter = patientAdapter;
    }

    //Create Patient
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        //convert dto to entity
        Patient patient= patientAdapter.toEntity(patientRequestDto);
        //save to DB
        Patient savedPatient=patientRepository.save(patient);
        //convert entity to responseDto and return
        return patientAdapter.toResponseDto(savedPatient);
    }

    // Get all active Patients
    public List<PatientResponseDto> getAllPatients(){
        //find all active patients
        List<Patient> patients= patientRepository.findByStatus(Status.ACTIVE);
        //convert each patient to response fto using for loop
        List<PatientResponseDto>patientResponseDtos=new ArrayList<>();

        for(Patient patient:patients){
            PatientResponseDto patientResponseDto= patientAdapter.toResponseDto(patient);
            patientResponseDtos.add(patientResponseDto);
        }
        return patientResponseDtos;
    }

    //Get Patient By id
    public PatientResponseDto getPatientById(Long id){
        //Fetch patient from DB
        Optional<Patient> patientOptional=patientRepository.findById(id);
        //check if patient exist
        if(!patientOptional.isPresent()){
            throw new RuntimeException("Patient not found with id: "+id);
        }
        Patient patient=patientOptional.get();
        //convert to responseDto and return
        return patientAdapter.toResponseDto(patient);
    }

    //update patient
    public PatientResponseDto updatePatient(Long id, PatientRequestDto patientRequestDto) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (!patientOptional.isPresent()) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        Patient existingPatient = patientOptional.get();
        //update fields
        if (patientRequestDto.getName() != null) {
            existingPatient.setName(patientRequestDto.getName());
        }
        if (patientRequestDto.getEmail() != null) {
            existingPatient.setEmail(patientRequestDto.getEmail());
        }
        if (patientRequestDto.getPhone() != null) {
            existingPatient.setPhone(patientRequestDto.getPhone());
        }
        if (patientRequestDto.getAddress() != null) {
            existingPatient.setAddress(patientRequestDto.getAddress());
        }
        if (patientRequestDto.getGender() != null) {
            existingPatient.setGender(patientRequestDto.getGender());
        }
        if (patientRequestDto.getDateOfBirth() != null) {
            existingPatient.setDateOfBirth(patientRequestDto.getDateOfBirth());
        }
        if (patientRequestDto.getBloodGroup() != null) {
            existingPatient.setBloodGroup(patientRequestDto.getBloodGroup());
        }
        if (patientRequestDto.getAllergies() != null) {
            existingPatient.setAllergies(patientRequestDto.getAllergies());
        }
        if (patientRequestDto.getMedicalHistory() != null) {
            existingPatient.setMedicalHistory(patientRequestDto.getMedicalHistory());
        }
        if (patientRequestDto.getCurrentTreatment() != null) {
            existingPatient.setCurrentTreatment(patientRequestDto.getCurrentTreatment());
        }

        //save updated patient
        Patient updatedPatient = patientRepository.save(existingPatient);

        //convert to response dto and Return
        return patientAdapter.toResponseDto(updatedPatient);
    }

    //Deactivate Patient (soft delete)
    public String deactivatePatient(Long id){
        //check if patient exist
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (!patientOptional.isPresent()) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        Patient patient = patientOptional.get();
        //set status to inactive
        patient.setStatus(Status.INACTIVE);
        //save
        patientRepository.save(patient);

        return "Patient deactivated successfully";
    }

    //Activate Patient
    public String activatePatient(Long id){
        //check if patient exist
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (!patientOptional.isPresent()) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        Patient patient = patientOptional.get();
        //set status to inactive
        patient.setStatus(Status.ACTIVE);
        //save
        patientRepository.save(patient);

        return "Patient activated successfully";
    }

}
