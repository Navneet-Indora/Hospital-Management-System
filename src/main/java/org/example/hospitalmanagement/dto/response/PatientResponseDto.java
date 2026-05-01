package org.example.hospitalmanagement.dto.response;

import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.BloodGroup;
import org.example.hospitalmanagement.Entity.Enums.Gender;
import org.example.hospitalmanagement.Entity.Enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {
    private Long id;
    //personal info
    private String name;
    private String email;
    private String phone;
    private String address;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Integer age;
    //Medical Info
    private BloodGroup bloodGroup;
    private String allergies;
    private String medicalHistory;
    private String currentTreatment;
    private Status status;
}
