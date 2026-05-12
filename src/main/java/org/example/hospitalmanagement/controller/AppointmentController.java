package org.example.hospitalmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.hospitalmanagement.dto.request.AppointmentRequestDto;
import org.example.hospitalmanagement.dto.response.AppointmentResponseDto;
import org.example.hospitalmanagement.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@Tag(name="Appointment Management",description = "APIs for managing appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //Create Appointment
    @Operation(summary = "Create new appointment",description = "Create a new appointment in the system ")
    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@Valid @RequestBody AppointmentRequestDto appointmentRequestDto) {
        AppointmentResponseDto response = appointmentService.createAppointment(appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get All Appointments
    @Operation(summary = "Get all appointments",description = "Returns a list of all appointments")
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<AppointmentResponseDto> response = appointmentService.getAllAppointments();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Appointments By id
    @Operation(summary = "Get appointment by ID",description = "Returns appointment by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDto response = appointmentService.getAppointmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Appointments By Patient id
    @Operation(summary = "Get appointment by patient ID",description = "Returns appointment by their doctor ID")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<AppointmentResponseDto> response = appointmentService.getAppointmentsByPatient(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Appointments By Doctor id
    @Operation(summary = "Get appointment by doctor ID",description = "Returns appointment by doctor ID")
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<AppointmentResponseDto> response = appointmentService.getAppointmentsByDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Reschedule Appointment
    @Operation(summary = "Reschedule appointment",description = "Reschedule a appointment")
    @PatchMapping("/{id}/reschedule")
    public ResponseEntity<AppointmentResponseDto> reschduleAppointment(@PathVariable Long id,@RequestBody AppointmentRequestDto appointmentRequestDto){
        AppointmentResponseDto response=appointmentService.rescheduleAppointment(id,appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Cancel Appointment
    @Operation(summary = "Cancel appointment",description = "Cancel a appointment")
    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id){
        String response=appointmentService.cancelAppointment(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Complete Appointment
    @Operation(summary = "Complete appointment",description = "Complete a appointment")
    @PutMapping("/{id}/complete")
    public ResponseEntity<String> completeAppointment(@PathVariable Long id){
        String response=appointmentService.completeAppointment(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

