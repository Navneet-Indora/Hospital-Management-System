package org.example.hospitalmanagement.dto.request;

import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.BloodGroup;
import org.example.hospitalmanagement.Entity.Enums.Gender;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto {
    //Personal Info - Client send these
    private String name;
    private String email;
    private String phone;
    private String address;
    private Gender gender;
    private LocalDate dateOfBirth;

    //Medical Info
    private BloodGroup bloodGroup;
    private String allergies;
    private String medicalHistory;
    private String currentTreatment;
}
