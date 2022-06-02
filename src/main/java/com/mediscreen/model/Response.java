package com.mediscreen.model;

import org.springframework.http.HttpStatus;

public class Response<T> {
	private HttpStatus status;
	private T data;
	private String errorCode;
	private String errorDescription;
	public HttpStatus getStatus() {
		return status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
