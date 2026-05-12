package org.example.hospitalmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.hospitalmanagement.dto.request.DoctorRequestDto;
import org.example.hospitalmanagement.dto.response.DoctorResponseDto;
import org.example.hospitalmanagement.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@Tag(name="Doctor Management",
        description = "APIs for managing doctor profiles")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    //Create Doctor
    //Post/api/v1/doctors
    @Operation(summary = "Create new doctor",
            description ="create a new doctor profile in the system" )
    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@Valid @RequestBody DoctorRequestDto doctorRequestDto){
        DoctorResponseDto response=doctorService.createDoctor(doctorRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get All Active Doctor
    //Get/api/v1/doctors
    @Operation(summary = "Get all active doctors",
            description = "Returns list of all active doctors")
    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){
        List<DoctorResponseDto> response=doctorService.getAllDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Doctor By id
    //Get/api/v1/doctors/1
    @Operation(summary ="Get doctor by ID",
            description = "Returns a doctor by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id){
        DoctorResponseDto response=doctorService.getDoctorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Update Doctor (Partial update)
    //Patch/api/v1/doctors/1
    @Operation(summary = "Update doctor",
            description = "Updates Specific fields of a doctor")
    @PatchMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id,@Valid @RequestBody DoctorRequestDto doctorRequestDto){
        DoctorResponseDto response=doctorService.updateDoctor(id,doctorRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Deactivate Doctor (soft delete)
    //Delete/api/v1/doctors/1/deactivate
    @Operation(summary="Deactivate doctor",
            description = "Soft deletes a doctor by setting status to INACTIVE")
    @DeleteMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateDoctor(@PathVariable Long id){
        String response= doctorService.deactivateDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Activate Doctor
    //PUT/api/v1/doctors/1/activate
    @Operation(summary = "Activate doctor",
            description ="Activates a doctor by setting status to ACTIVE" )
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateDoctor(@PathVariable Long id){
        String response=doctorService.activateDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
