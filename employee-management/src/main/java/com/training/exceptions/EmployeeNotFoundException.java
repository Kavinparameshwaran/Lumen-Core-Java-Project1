package com.training.exceptions;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception{
	private String errorCode;
	public EmployeeNotFoundException(String errorCode,String message) {
		super(message);
		this.errorCode=errorCode;
	}
	@Override
	public String getMessage() {
		return this.errorCode+":"+super.getMessage();
	}

}
