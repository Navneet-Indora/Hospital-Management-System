package org.example.hospitalmanagement.service;

import org.example.hospitalmanagement.Adapter.DoctorAdapter;
import org.example.hospitalmanagement.Entity.Doctor;
import org.example.hospitalmanagement.Entity.Enums.Status;
import org.example.hospitalmanagement.dto.request.DoctorRequestDto;
import org.example.hospitalmanagement.dto.response.DoctorResponseDto;
import org.example.hospitalmanagement.exception.DuplicateResourceException;
import org.example.hospitalmanagement.exception.ResourceNotFoundException;
import org.example.hospitalmanagement.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorAdapter doctorAdapter;

    public DoctorService(DoctorRepository doctorRepository, DoctorAdapter doctorAdapter) {
        this.doctorRepository = doctorRepository;
        this.doctorAdapter = doctorAdapter;
    }

    //Create Doctor
    public DoctorResponseDto createDoctor(DoctorRequestDto doctorRequestDto){
        //check duplicate email
        if(doctorRepository.existsByEmail(doctorRequestDto.getEmail())){
            throw new DuplicateResourceException("Doctor already Exists with email:"+doctorRequestDto.getEmail());
        }
        //convert dto to entity
        Doctor doctor=doctorAdapter.toEntity(doctorRequestDto);
        //save to database
        Doctor savedDoctor=doctorRepository.save(doctor);
        //convert entity to response dto and return
        return doctorAdapter.toResponseDto(savedDoctor);
    }

    //Get All Active Doctors
    public List<DoctorResponseDto> getAllDoctors(){
        //fetch all active doctors from db
        List<Doctor>doctors=doctorRepository.findByStatus(Status.ACTIVE);
        //covert each to doctor to response dto
        List<DoctorResponseDto>doctorResponseDtos=new ArrayList<>();
        for(Doctor doctor:doctors){
            DoctorResponseDto doctorResponseDto=doctorAdapter.toResponseDto(doctor);
            doctorResponseDtos.add(doctorResponseDto);
        }
        return doctorResponseDtos;
    }

    //Get Doctor By id
    public DoctorResponseDto getDoctorById(Long id){
        //fetch doctor from db
        Optional<Doctor> doctorOptional=doctorRepository.findById(id);
        //check if doctor exists
        if(!doctorOptional.isPresent()){
            throw new ResourceNotFoundException("Doctor not found with id: "+id);
        }
        Doctor doctor=doctorOptional.get();
        //convert to response dto and return
        return doctorAdapter.toResponseDto(doctor);
    }

    //Update Doctor
    public DoctorResponseDto updateDoctor(Long id,DoctorRequestDto doctorRequestDto) {
        //fetch doctor from db
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        //check if doctor exists
        if (!doctorOptional.isPresent()) {
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        }
        Doctor existingDoctor = doctorOptional.get();
        //only update fields that cliect sent(not null)
        if (doctorRequestDto.getName() != null) {
            existingDoctor.setName(doctorRequestDto.getName());
        }
        if (doctorRequestDto.getEmail() != null) {
            existingDoctor.setEmail(doctorRequestDto.getEmail());
        }
        if (doctorRequestDto.getPhone() != null) {
            existingDoctor.setPhone(doctorRequestDto.getPhone());
        }
        if (doctorRequestDto.getAddress() != null) {
            existingDoctor.setAddress(doctorRequestDto.getAddress());
        }
        if (doctorRequestDto.getGender() != null) {
            existingDoctor.setGender(doctorRequestDto.getGender());
        }
        if (doctorRequestDto.getSpecialization() != null) {
            existingDoctor.setSpecialization(doctorRequestDto.getSpecialization());
        }
        if(doctorRequestDto.getQualification()!=null){
            existingDoctor.setQualification(doctorRequestDto.getQualification());
        }
        if(doctorRequestDto.getExperienceYears()!=null){
            existingDoctor.setExperienceYears(doctorRequestDto.getExperienceYears());
        }
        if(doctorRequestDto.getConsultationFees()!=null){
            existingDoctor.setConsultationFees(doctorRequestDto.getConsultationFees());
        }
        if(doctorRequestDto.getAvailableDays()!=null){
            existingDoctor.setAvailableDays(doctorRequestDto.getAvailableDays());
        }
        if(doctorRequestDto.getAvailableTime()!=null){
            existingDoctor.setAvailableTime(doctorRequestDto.getAvailableTime());
        }
        //save updated doctor
        Doctor updatedDoctor=doctorRepository.save(existingDoctor);
        //covert to response dto and return
        return doctorAdapter.toResponseDto(updatedDoctor);
    }

    //Deactivate doctor(soft delete)
    public String deactivateDoctor(Long id){
        //fetch doctor from db
        Optional<Doctor> doctorOptional=doctorRepository.findById(id);
        //check if doctor exists
        if(!doctorOptional.isPresent()){
            throw new ResourceNotFoundException("Doctor not found with id: "+id);
        }
        Doctor doctor=doctorOptional.get();
        //set status to INACTIVE
        doctor.setStatus(Status.INACTIVE);
        //save
        doctorRepository.save(doctor);
        return "Doctor deactivated successfully";
    }

    //Activate doctor
    public String activateDoctor(Long id){
        //fetch doctor from db
        Optional<Doctor> doctorOptional=doctorRepository.findById(id);
        //check if doctor exists
        if(!doctorOptional.isPresent()){
            throw new ResourceNotFoundException("Doctor not found with id: "+id);
        }
        Doctor doctor=doctorOptional.get();
        //set status to ACTIVE
        doctor.setStatus(Status.ACTIVE);
        //save
        doctorRepository.save(doctor);
        return "Doctor activated successfully";
    }

}
