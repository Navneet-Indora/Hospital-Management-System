package org.example.hospitalmanagement.Adapter.Impl;

import org.example.hospitalmanagement.Adapter.AppointmentAdapter;
import org.example.hospitalmanagement.Entity.Appointment;
import org.example.hospitalmanagement.Entity.Doctor;
import org.example.hospitalmanagement.Entity.Patient;
import org.example.hospitalmanagement.dto.request.AppointmentRequestDto;
import org.example.hospitalmanagement.dto.response.AppointmentResponseDto;
import org.example.hospitalmanagement.repository.DoctorRepository;
import org.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class AppointmentAdapterImpl implements AppointmentAdapter {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentAdapterImpl(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }


    @Override
    public Appointment toEntity(AppointmentRequestDto appointmentRequestDto) {
        Patient patient= patientRepository.findById(appointmentRequestDto.getPatientId())
                .orElseThrow(() ->  new RuntimeException("Patient not found with id "+appointmentRequestDto.getPatientId()));

        Doctor doctor=doctorRepository.findById(appointmentRequestDto.getDoctorId())
                .orElseThrow(()-> new RuntimeException("Doctor not found with id "+appointmentRequestDto.getDoctorId()));

        return Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .appointmentDate(appointmentRequestDto.getAppointmentDate())
                .appointmentTime(appointmentRequestDto.getAppointmentTime())
                .reason(appointmentRequestDto.getReason())
                .build();
    }

    @Override
    public AppointmentResponseDto toResponseDto(Appointment appointment) {
        return AppointmentResponseDto.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatient().getId())
                .patientName(appointment.getPatient().getName())
                .doctorId(appointment.getDoctor().getId())
                .doctorName(appointment.getDoctor().getName())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .reason(appointment.getReason())
                .notes(appointment.getNotes())
                .appointmentStatus(appointment.getAppointmentStatus())
                .patientNotified(appointment.getPatientNotified())
                .doctorNotified(appointment.getDoctorNotified())
                .build();
    }
}
