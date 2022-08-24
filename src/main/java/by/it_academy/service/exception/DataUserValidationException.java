package by.it_academy.service.exception;

import java.util.Map;

import by.it_academy.util.InputDataUserValidation;

public class DataUserValidationException extends Exception{
	

	private static final long serialVersionUID = 1L;
	
	private Map<InputDataUserValidation, String> errorMap;
	
	public DataUserValidationException(String message) {
		super(message);
	}
	
	public DataUserValidationException(Exception e) {
		super(e);
	}
	
	public DataUserValidationException(String message, Exception e) {
		super(message, e);
	}
	
	public DataUserValidationException(Map<InputDataUserValidation, String> errorMap, String message) {
		super(message);
		this.errorMap = errorMap;
	}

	public Map<InputDataUserValidation, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<InputDataUserValidation, String> errorMap) {
		this.errorMap = errorMap;
	}
	
	
	

}
