package com.mediscreen.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mediscreen.model.Response;


public class Utility {
	
	public ResponseEntity<Response> createResponseWithErrors(String errorCode, String errorDescription) {
		Response response = new Response(); 
		response.setData(null);
		response.setStatus(HttpStatus.OK);
		response.setErrorCode(String.format("%s", errorCode));
		response.setErrorDescription(errorDescription);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public <T>void createResponseWithSuccess(Response response, T data) {
		response.setData(data);
		response.setStatus(HttpStatus.OK);
		response.setErrorCode(null);
		response.setErrorDescription(null);
	}

}
