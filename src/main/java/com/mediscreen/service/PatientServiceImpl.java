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

import com.mediscreen.exception.PatientDuplicateException;
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
	public ResponseEntity<Response> savePatient(Patient patient) throws PatientDuplicateException {
		logger.debug("Calling create patient");
		String errorDescription = "";
		if (patient == null) {
			errorDescription = "Enter a valid Patient !"; 
			return utility.createResponseWithErrors(Constant.ERROR_MESSAGE_PATIENT_REQUIRED, errorDescription);
		}
		Optional<Patient> patientOpt = getPatientByFirstNameAndLastName(patient.getFirstName(), patient.getLastName());
		
		Patient patientResult = patientOpt.map(Function.identity()).orElse(null);
		if (patientResult != null) {
			throw new PatientDuplicateException("Record already exists");
		}
		patientRepository.save(patient);
		
		new Utility().createResponseWithSuccess(response, patient);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Response> getPatientById(int patientId) {
		logger.debug("Calling get patient by Id");
		String errorDescription = "";
		if (patientId == 0) {
			errorDescription = "Enter a valid idPatient !"; 
			return utility.createResponseWithErrors(Constant.ERROR_MESSAGE_IDPATIENT_REQUIRED, errorDescription);
		}
		Patient patient = patientRepository.findById(patientId).orElse(null);
		
		new Utility().createResponseWithSuccess(response, patient);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response> updatePatient(Patient patient) {
		logger.debug("Calling update patient info");
		String errorDescription = "";
		if (patient == null) {
			errorDescription = "Enter a valid Patient !"; 
			return utility.createResponseWithErrors(Constant.ERROR_MESSAGE_PATIENT_REQUIRED, errorDescription);
		}
		Patient existingPatient = patientRepository.findById(patient.getPatientId()).orElseThrow(null);
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
		logger.debug("Calling get all patients");
		return patientRepository.findAll();
	}

	@Override
	public Optional<Patient> getPatientByFirstNameAndLastName(String firstName, String lastName) {
		logger.debug("Calling get patient by first name and last name");
		return patientRepository.findByFirstNameAndLastName(firstName, lastName).stream().findFirst();
	}
	
}
