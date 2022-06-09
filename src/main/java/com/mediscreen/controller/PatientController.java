package com.mediscreen.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.exception.BodyNotValidException;
import com.mediscreen.exception.PatientDuplicateException;
import com.mediscreen.exception.PatientNotFoundException;
import com.mediscreen.model.Patient;
import com.mediscreen.model.Response;
import com.mediscreen.repository.PatientRepository;
import com.mediscreen.service.IPatientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
public class PatientController {
	
	static final Logger logger = LogManager.getLogger(PatientController.class);
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private IPatientService patientService;
	
	@GetMapping("/")
	public String index() {
		return "Welcome on REST API which communicate with Sql database.";
	}
	
	@GetMapping(value = "/patients")
	public List<Patient> getAllPatient() {
		return patientRepository.findAll();
	}
	
	@GetMapping(value = "/patientById")
	public ResponseEntity<Response> getPatientById(@RequestParam Integer patientId) {
		return patientService.getPatientById(patientId);
	}
	
	@RequestMapping(value="/patient")
	@ResponseBody
	public ResponseEntity<Response> createPatient(@NotNull @RequestBody final Patient patient) throws PatientDuplicateException {
		logger.info("createPatient()");
		return patientService.savePatient(patient);
	}
	
	@PutMapping( value = "/patientUpdated")
	public ResponseEntity<Response> updatePatient(@RequestBody Patient patient) {
		logger.info("update a patient");
		return patientService.updatePatient(patient);
			
	}
			

			
	/*@PostMapping( value = "/patientToSave")
	public Patient savePatient(@RequestBody Patient patient) {
		return patientRepository.save(patient);
	
}
	@PostMapping("/savePatient")
	public String savePatient(@Valid @RequestBody Patient patient, BindingResult bindingResult,
			HttpServletResponse httpServletResponse) {
		if (bindingResult.hasErrors()) {
			return new PatientNotFoundException(
					"The patient provided : '" + patient.toString() + "' doesn't satisfy the required field: '"
							+ bindingResult.getFieldError().getDefaultMessage() + "'.").toString();
		}

		httpServletResponse.setStatus(201);
		patientRepository.save(patient);
		return "Patient saved with success";
	}*/
	
	@GetMapping(value = "/patientByFirstAndLastName")
	public Optional<Patient> getPatientByFirstAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
		return patientService.getPatientByFirstNameAndLastName(firstName, lastName);
	}
}
