package com.mediscreen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mediscreen.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>, JpaSpecificationExecutor<Patient> {
	
	@Query("FROM Patient p WHERE p.firstName = :firstName and p.lastName = :lastName ORDER BY p.patientId")
	List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
