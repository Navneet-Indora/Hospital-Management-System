package org.example.hospitalmanagement.controller;

import org.example.hospitalmanagement.dto.request.DoctorRequestDto;
import org.example.hospitalmanagement.dto.response.DoctorResponseDto;
import org.example.hospitalmanagement.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    //Create Doctor
    //Post/api/v1/doctors
    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        DoctorResponseDto response=doctorService.createDoctor(doctorRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get All Active Doctor
    //Get/api/v1/doctors
    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){
        List<DoctorResponseDto> response=doctorService.getAllDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Doctor By id
    //Get/api/v1/doctors/1
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id){
        DoctorResponseDto response=doctorService.getDoctorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Update Doctor (Partial update)
    //Patch/api/v1/doctors/1
    @PatchMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id,@RequestBody DoctorRequestDto doctorRequestDto){
        DoctorResponseDto response=doctorService.updateDoctor(id,doctorRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Deactivate Doctor (soft delete)
    //Delete/api/v1/doctors/1/deactivate
    @DeleteMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateDoctor(@PathVariable Long id){
        String response= doctorService.deactivateDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Activate Doctor
    //PUT/api/v1/doctors/1/activate
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateDoctor(@PathVariable Long id){
        String response=doctorService.activateDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
