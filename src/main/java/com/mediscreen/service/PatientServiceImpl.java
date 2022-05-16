package com.mediscreen.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mediscreen.model.Patient;
import com.mediscreen.model.Response;
import com.mediscreen.repository.PatientRepository;
import com.mediscreen.utility.Constant;
import com.mediscreen.utility.Utility;

@Service
public class PatientServiceImpl implements IPatientService {
	
	final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
	
	@Autowired
	private PatientRepository patientRepository;
	
	private Utility utility;
	
	private Response response;
	
	public PatientServiceImpl() {
		utility = new Utility();
		response = new Response();
	}

	@Override
	public ResponseEntity<Response> savePatient(Patient patient) {
		String errorDescription = "";
		if (patient == null) {
			errorDescription = "Enter a valid Patient !"; 
			return utility.createResponseWithErrors(Constant.ERROR_MESSAGE_PATIENT_REQUIRED, errorDescription);
		}
		Optional<Patient> patientOpt = getPatientByFirstNameAndLastName(patient.getFirstName(), patient.getLastName());
		
		Patient patientResult = patientOpt.map(Function.identity()).orElse(null);
		if (patientResult != null) {
			throw new RuntimeException("Record already exists");
		}
		patientRepository.save(patient);
		
		new Utility().createResponseWithSuccess(response, patient);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Response> getPatientById(int idPatient) {
		String errorDescription = "";
		if (idPatient == 0) {
			errorDescription = "Enter a valid idPatient !"; 
			return utility.createResponseWithErrors(Constant.ERROR_MESSAGE_IDPATIENT_REQUIRED, errorDescription);
		}
		patientRepository.findById(idPatient).orElse(null);
		
		new Utility().createResponseWithSuccess(response, idPatient);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response> updatePatient(Patient patient) {
		String errorDescription = "";
		if (patient == null) {
			errorDescription = "Enter a valid Patient !"; 
			return utility.createResponseWithErrors(Constant.ERROR_MESSAGE_PATIENT_REQUIRED, errorDescription);
		}
		Patient existingPatient = patientRepository.findById(patient.getIdPatient()).orElse(null);
		if (existingPatient == null) {
			errorDescription = "Patient not found !"; 
			return utility.createResponseWithErrors(Constant.ERROR_MESSAGE_PATIENT_REQUIRED, errorDescription);
		}
		existingPatient.setFirstName(patient.getFirstName());
		existingPatient.setLastName(patient.getLastName());
		existingPatient.setBirthDate(patient.getBirthDate());
		existingPatient.setSex(patient.getSex());
		existingPatient.setAddress(patient.getAddress());
		existingPatient.setPhone(patient.getPhone());
		patientRepository.save(existingPatient);
		
		new Utility().createResponseWithSuccess(response, patient);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}
	
	@Override
	public Optional<Patient> getPatientByFirstNameAndLastName(String firstName, String lastName) {
		return patientRepository.findByFirstNameAndLastName(firstName, lastName);
	}
	
	
}
