package org.example.hospitalmanagement.repository;

import org.example.hospitalmanagement.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    //get all appointments of a patient
    List<Appointment> findByPatientId(Long patientId);
    //get all appointments of a doctor
    List<Appointment> findByDoctorId(Long doctorId);
    //check doctor clash
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(Long doctorId, LocalDate appointmentDate, LocalTime appointmentTime);
    //check patient clash
    boolean existsByPatientIdAndAppointmentDateAndAppointmentTime(Long patientId, LocalDate appointmentDate, LocalTime appointmentTime);
}
