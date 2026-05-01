package org.example.hospitalmanagement.controller;

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
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //Create Appointment
    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {
        AppointmentResponseDto response = appointmentService.createAppointment(appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get All Appointments
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<AppointmentResponseDto> response = appointmentService.getAllAppointments();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Appointments By id
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDto response = appointmentService.getAppointmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Appointments By Patient id
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<AppointmentResponseDto> response = appointmentService.getAppointmentsByPatient(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Get Appointments By Doctor id
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<AppointmentResponseDto> response = appointmentService.getAppointmentsByDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Reschedule Appointment
    @PatchMapping("/{id}/reschedule")
    public ResponseEntity<AppointmentResponseDto> reschduleAppointment(@PathVariable Long id,@RequestBody AppointmentRequestDto appointmentRequestDto){
        AppointmentResponseDto response=appointmentService.rescheduleAppointment(id,appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Cancel Appointment
    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id){
        String response=appointmentService.cancelAppointment(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Complete Appointment
    @PutMapping("/{id}/complete")
    public ResponseEntity<String> completeAppointment(@PathVariable Long id){
        String response=appointmentService.completeAppointment(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

