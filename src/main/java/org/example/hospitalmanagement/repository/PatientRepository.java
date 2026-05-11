package org.example.hospitalmanagement.repository;

import org.example.hospitalmanagement.Entity.Enums.Status;
import org.example.hospitalmanagement.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByStatus(Status status);


    boolean existsByEmail(String email);
}
