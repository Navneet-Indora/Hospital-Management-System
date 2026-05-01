package org.example.hospitalmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hospitalmanagement.Entity.Enums.BloodGroup;
import org.example.hospitalmanagement.Entity.Enums.Gender;
import org.example.hospitalmanagement.Entity.Enums.Status;

import java.time.LocalDate;

@Entity
@Table(name="patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //personal info
    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    private Integer age;

    //Medical Info
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;   //A+,B+,O+ etc

    @Column(length=1000)
    private String allergies;   //any allergies

    @Column(length=2000)
    private String medicalHistory;   //past diseases, surgeries

    @Column(length=2000)
    private String currentTreatment;   //current Treatment Plan

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status=Status.ACTIVE;    //Active , Inactive


}
