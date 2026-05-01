package org.example.hospitalmanagement.repository;

import org.example.hospitalmanagement.Entity.Doctor;
import org.example.hospitalmanagement.Entity.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor>findByStatus(Status status);
}
