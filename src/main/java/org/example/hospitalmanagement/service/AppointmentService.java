package org.example.hospitalmanagement.service;

import org.example.hospitalmanagement.Adapter.AppointmentAdapter;
import org.example.hospitalmanagement.Entity.Appointment;
import org.example.hospitalmanagement.Entity.Enums.AppointmentStatus;
import org.example.hospitalmanagement.dto.request.AppointmentRequestDto;
import org.example.hospitalmanagement.dto.response.AppointmentResponseDto;
import org.example.hospitalmanagement.exception.ResourceNotFoundException;
import org.example.hospitalmanagement.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentAdapter appointmentAdapter;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentAdapter appointmentAdapter) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentAdapter = appointmentAdapter;
    }

    //create appointment
    public AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentRequestDto){
        //convert dto to entity
        //Adapter fetches patient & doctor from DB automatically
        Appointment appointment= appointmentAdapter.toEntity(appointmentRequestDto);
        //save to database
        Appointment savedAppointment=appointmentRepository.save(appointment);
        //convert entity to Responsedto and return
        return appointmentAdapter.toResponseDto(savedAppointment);
    }
    //Get All Appointments
    public List<AppointmentResponseDto> getAllAppointments(){
        //Fetch all appointments from DB
        List<Appointment> appointments=appointmentRepository.findAll();
         // convert each appointment to ResponseDto
        List<AppointmentResponseDto>appointmentResponseDtos=new ArrayList<>();
        for(Appointment appointment:appointments){
            AppointmentResponseDto appointmentResponseDto=appointmentAdapter.toResponseDto(appointment);
            appointmentResponseDtos.add(appointmentResponseDto);
        }
        return appointmentResponseDtos;
    }

    //Get Appointment By id
    public AppointmentResponseDto getAppointmentById(Long id){
        //fetch appointment from DB
        Optional<Appointment> appointmentOptional= appointmentRepository.findById(id);
        //Check if Appointment Exists
        if(!appointmentOptional.isPresent()){
            throw new ResourceNotFoundException("Appointment not found with id :"+id);
        }
        Appointment appointment=appointmentOptional.get();
        //convert to ResponseDto and Return
        return appointmentAdapter.toResponseDto(appointment);
    }

    //Get Appointments By Patient
    public List<AppointmentResponseDto> getAppointmentsByPatient(Long patientId){
        //fetch all appointments of a patient
        List<Appointment> appointments= appointmentRepository.findByPatientId(patientId);
        //convert each to ResponseDto

        List<AppointmentResponseDto>appointmentResponseDtos=new ArrayList<>();

        for(Appointment appointment:appointments){
            AppointmentResponseDto appointmentResponseDto= appointmentAdapter.toResponseDto(appointment);
            appointmentResponseDtos.add(appointmentResponseDto);
        }
        return appointmentResponseDtos;
    }

    //Get Appointments By doctor
    public List<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId){
        //fetch all appointments of a doctor
        List<Appointment>appointments=appointmentRepository.findByDoctorId(doctorId);
        //convert each to ResponseDto
        List<AppointmentResponseDto>appointmentResponseDtos= new ArrayList<>();

        for(Appointment appointment: appointments){
            AppointmentResponseDto appointmentResponseDto=appointmentAdapter.toResponseDto(appointment);
            appointmentResponseDtos.add(appointmentResponseDto);
        }
        return appointmentResponseDtos;
    }

    //ReSchedule Appointment
    public AppointmentResponseDto rescheduleAppointment(Long id,AppointmentRequestDto appointmentRequestDto){
        //check if appointment exists
        Optional<Appointment> appointmentOptional= appointmentRepository.findById(id);
        //Check if Appointment Exists
        if(!appointmentOptional.isPresent()){
            throw new ResourceNotFoundException("Appointment not found with id :"+id);
        }
        Appointment existingAppointment=appointmentOptional.get();
        //save old date and time before updating
        existingAppointment.setPreviousDate(existingAppointment.getAppointmentDate());
        existingAppointment.setPreviousTime(existingAppointment.getAppointmentTime());
         //update date and time
        if(appointmentRequestDto.getAppointmentDate()!=null) {
            existingAppointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
        }
        if(appointmentRequestDto.getAppointmentTime()!=null) {
            existingAppointment.setAppointmentTime(appointmentRequestDto.getAppointmentTime());
        }
        //Update status to RESCHEDULED
        existingAppointment.setAppointmentStatus(AppointmentStatus.RESCHEDULED);
        //save
        Appointment updatedAppointment=appointmentRepository.save(existingAppointment);
        //convert to responseDto and return
        return appointmentAdapter.toResponseDto(updatedAppointment);
    }

    //cancel Appointment
    public String cancelAppointment(Long id){
        //check if appointment exists
        Optional<Appointment> appointmentOptional= appointmentRepository.findById(id);
        //Check if Appointment Exists
        if(!appointmentOptional.isPresent()){
            throw new ResourceNotFoundException("Appointment not found with id :"+id);
        }
        Appointment appointment=appointmentOptional.get();
        //set status to CANCELLED
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        //save
        appointmentRepository.save(appointment);

        return "Appointment cancelled successfully";
    }

    //Complete Appointment
    public String completeAppointment(Long id){
        //check if appointment exists
        Optional<Appointment> appointmentOptional= appointmentRepository.findById(id);
        //Check if Appointment Exists
        if(!appointmentOptional.isPresent()){
            throw new ResourceNotFoundException("Appointment not found with id :"+id);
        }
        Appointment appointment=appointmentOptional.get();
        //set status to Completed
        appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
        //save
        appointmentRepository.save(appointment);

        return "Appointment completed successfully";
    }
}
