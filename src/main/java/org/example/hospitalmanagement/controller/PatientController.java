package org.example.hospitalmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.hospitalmanagement.dto.request.PatientRequestDto;
import org.example.hospitalmanagement.dto.response.PatientResponseDto;
import org.example.hospitalmanagement.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name="Patient Management",
        description = "APIs for managing patient profiles")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Create Patient
    //Post/api/v1/patietns
    @Operation(summary="Create new patient",
               description = "Create a new patient profile in the system")
    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto response = patientService.createPatient(patientRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get all active patients
    @Operation(summary = "Get all active patients",
            description = "Returns list of all active patients")
    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        List<PatientResponseDto> response = patientService.getAllPatients();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Patient By id
    //GET/api/v1/patients/id
    @Operation(summary ="Get patient by ID",
               description = "Returns a patient by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {
        PatientResponseDto response = patientService.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //update Patient
    //Patch/api/v1/patients/1
    @Operation(summary = "Update patient",
               description = "Updates Specific fields of a patient")
    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponseDto> udpatePatient(@PathVariable Long id,@Valid @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto response=patientService.updatePatient(id,patientRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Deactivate Patient
    //Delete/api/v1/patients/id/deactivate
    @Operation(summary="Deactivate Patient",
               description = "Soft deletes a patient by setting status to INACTIVE")
    @DeleteMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivatePatient(@PathVariable Long id){
        String response=patientService.deactivatePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Activate Patient
    //Put/api/v1/patients/1/activate
    @Operation(summary = "Activate Patient",
               description ="Activates a patient by setting status to ACTIVE" )
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activatePatient(@PathVariable Long id){
        String response= patientService.activatePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
