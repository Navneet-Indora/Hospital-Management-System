package org.example.hospitalmanagement.controller;

import org.example.hospitalmanagement.dto.request.PatientRequestDto;
import org.example.hospitalmanagement.dto.response.PatientResponseDto;
import org.example.hospitalmanagement.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Create Patient
    //Post/api/v1/patietns
    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto response = patientService.createPatient(patientRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get all active patients
    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        List<PatientResponseDto> response = patientService.getAllPatients();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Patient By id
    //GET/api/v1/patients/id
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {
        PatientResponseDto response = patientService.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //update Patient
    //Patch/api/v1/patients/1
    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponseDto> udpatePatient(@PathVariable Long id,@RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto response=patientService.updatePatient(id,patientRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Deactivate Patient
    //Delete/api/v1/patients/id/deactivate
    @DeleteMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivatePatient(@PathVariable Long id){
        String response=patientService.deactivatePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Activate Patient
    //Put/api/v1/patients/1/activate
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activatePatient(@PathVariable Long id){
        String response= patientService.activatePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
