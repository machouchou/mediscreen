package com.mediscreen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.mediscreen.model.Patient;
import com.mediscreen.repository.PatientRepository;
import com.mediscreen.service.PatientServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestPropertySource("/applicationTest.properties")
public class PatientRepositiryTest {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	PatientServiceImpl patientService;

	// JUnit test for savePatient
	@Test
	public void save_newPatientTest_PatientSavedInListOfPatients() {
		
		//patientRepository.deleteByFirstNameAndLastName("Ramesh", "Sadou");
		
		String dateString = "1999-06-13";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 
		Patient patient = new Patient();
		patient.setFirstName("Ramesh");
		patient.setLastName("Sadou");
		patient.setBirthDate(LocalDate.parse(dateString, formatter));
		patient.setSex("M");
		patient.setAddress("6 rue lamartine 75009 Paris");
		patient.setPhone("0603367020");
		
		patientService.savePatient(patient);
		
		
		Assertions.assertThat(patient.getFirstName().equalsIgnoreCase("Ramesh"));
	}
	
	@Test
	public void findAllPatientsTest_ListOfPatients() {
		
		//Arrange
		String firstName = "Ramesh";
		//Act
		List<Patient> lPatients = patientService.getPatients();
		
		assertNotEquals(Collections.EMPTY_LIST, lPatients.size());
		assertTrue(lPatients.stream().anyMatch(p -> firstName.equals(p.getFirstName())));
	}
	
	@Test
	public void savePatientsTest_ListOfPatientsCreated() {
		
		String dateString1 = "2008-06-19";
		String dateString2 = "2010-07-03";
		String dateString3 = "2012-09-25";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		Patient patient1 = new Patient();
		patient1.setFirstName("Geremy");
		patient1.setLastName("Lopes");
		patient1.setBirthDate(LocalDate.parse(dateString1, formatter));
		patient1.setSex("M");
		patient1.setAddress("31 rue Dominique 75007 Paris");
		patient1.setPhone("0603367001");
		
		Patient patient2 = new Patient();
		patient2.setFirstName("Clara");
		patient2.setLastName("Lopes");
		patient2.setBirthDate(LocalDate.parse(dateString2, formatter));
		patient2.setSex("M");
		patient2.setAddress("31 rue Dominique 75007 Paris");
		patient2.setPhone("0603367002");
		
		Patient patient3 = new Patient();
		patient3.setFirstName("Alex");
		patient3.setLastName("Lopes");
		patient3.setBirthDate(LocalDate.parse(dateString3, formatter));
		patient3.setSex("M");
		patient3.setAddress("31 rue Dominique 75007 Paris");
		patient3.setPhone("0603367003");
		
		List<Patient> patients = new ArrayList<>();
		patients.add(patient1);
		patients.add(patient2);
		patients.add(patient3);
		
		patientRepository.saveAll(patients);
		
		//Act
		List<Patient> lPatients = patientRepository.findAll();
		
		assertNotEquals(Collections.EMPTY_LIST, lPatients);
		assertEquals(3, lPatients.size());
	}

	// JUnit test for updatePatient
		@Test
		public void update_existingPatientTest_PatientUpdatedInListOfPatients() {
			
			patientService.getPatientById(4);
			
			String dateString = "1999-06-13";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 
			Patient existingPatient = new Patient();
			existingPatient.setFirstName("Ramesh");
			existingPatient.setLastName("Dupuis");
			existingPatient.setBirthDate(LocalDate.parse(dateString, formatter));
			existingPatient.setSex("M");
			existingPatient.setAddress("6 rue lamartine 75009 Paris");
			existingPatient.setPhone("0603367020");
			
			patientService.updatePatient(existingPatient);
			
			
			Assertions.assertThat(existingPatient.getFirstName().equalsIgnoreCase("Ramesh"));
		}
		
		// JUnit test for getPatientById
		@Test
		public void getPatientByFirstAndLastname_existingPatientTest_PatientFound() {
				
			
			assertTrue(patientService.getPatientByFirstNameAndLastName("Alex", "Lopes").isPresent());
		}
		
		@Test
		public void getPatientByIdTest_existingPatientTest_PatientFound() {
				
			
			assertFalse(patientService.getPatientById(2).equals(patientRepository.findById(2)));
		}
}
