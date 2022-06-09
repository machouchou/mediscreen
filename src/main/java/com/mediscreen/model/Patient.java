package com.mediscreen.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	@NotNull
	private int patientId;
	
	@Column(name = "first_name", nullable = false)
	@NotBlank(message = "The first name is mandatory")
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	@NotBlank(message = "The first name is mandatory")
	private String lastName;
	
	@Column(name = "birth_date", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	
	@Column(name = "sex", nullable = false)
	@Pattern(regexp = "[FM]")
	private String sex;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone", nullable = false)
	private String phone;

	public Patient() {
		super();
	}


	public int getPatientId() {
		return patientId;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	

}
