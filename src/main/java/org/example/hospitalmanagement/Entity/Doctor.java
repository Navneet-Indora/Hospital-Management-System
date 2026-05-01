package org.example.hospitalmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.Gender;
import org.example.hospitalmanagement.Entity.Enums.Qualification;
import org.example.hospitalmanagement.Entity.Enums.Specialization;
import org.example.hospitalmanagement.Entity.Enums.Status;

@Entity
@Table(name="doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Personal Info
    @Column(nullable = false)
    private String name;

    @Column(nullable= false,unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    //Professional Info
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Qualification qualification; //MBBS, MD

    private Integer experienceYears;

    private Double consultationFees;   // Fees per appointment

    private String availableDays; //Mon, Tue, Wed

    private String availableTime; //9Am-5PM

    //Status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status=Status.ACTIVE;
}
