package org.example.hospitalmanagement.dto.request;

import jakarta.validation.constraints.*;
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
    //Professional Info
    @NotNull(message="Specialization is required")
    private Specialization specialization;

    @NotNull(message = "Qualification is required")
    private Qualification qualification;

    @Min(value=0, message="Experience years cannot be negative")
    private Integer experienceYears;

    @Min(value=0, message = "Consultation fees cannot be negative")
    private Double consultationFees;
    private String availableDays;
    private String availableTime;
}
