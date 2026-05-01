package org.example.hospitalmanagement.dto.request;

import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.Gender;
import org.example.hospitalmanagement.Entity.Enums.Qualification;
import org.example.hospitalmanagement.Entity.Enums.Specialization;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {
    //Personal Info
    private String name;
    private String email;
    private String phone;
    private String address;
    private Gender gender;
    //Professional Info
    private Specialization specialization;
    private Qualification qualification;
    private Integer experienceYears;
    private Double consultationFees;
    private String availableDays;
    private String availableTime;
}
