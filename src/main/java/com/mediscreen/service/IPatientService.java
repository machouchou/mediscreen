package com.mediscreen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.mediscreen.model.Patient;
import com.mediscreen.model.Response;

public interface IPatientService {
	
	ResponseEntity<Response> savePatient(Patient patient);
	
	ResponseEntity<Response> getPatientById(int idPatient);
	
	ResponseEntity<Response> updatePatient(Patient patient);
	
	public List<Patient> getPatients();
	
	public Optional<Patient> getPatientByFirstNameAndLastName(String firstName, String lastName);
	
}
