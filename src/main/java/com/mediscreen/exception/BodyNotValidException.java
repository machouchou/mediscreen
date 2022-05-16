package com.mediscreen.exception;

public class BodyNotValidException extends RuntimeException{
	
	public BodyNotValidException(String errorMessage) {
		super(errorMessage);
	}

}
