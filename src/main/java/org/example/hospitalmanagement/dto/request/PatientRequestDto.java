package org.example.hospitalmanagement.dto.request;

import jakarta.validation.constraints.*;
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
    @NotBlank(message="Name is required")
    @Size(min=2, max=50, message="Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message="Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;
    private String address;
    private Gender gender;

    @Past(message = "Date of birth must be in past")
    private LocalDate dateOfBirth;

    //Medical Info
    private BloodGroup bloodGroup;
    private String allergies;
    private String medicalHistory;
    private String currentTreatment;
}
