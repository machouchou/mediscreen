package com.mediscreen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PatientDuplicateException extends Exception{
	
	public PatientDuplicateException(String errorMessage) {
        super(errorMessage);
    }

}
