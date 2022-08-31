package by.it_academy.service.exception;

import java.util.HashMap;
import java.util.Map;

import by.it_academy.util.InputDataNewsValidation;

public class DataNewsValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
private Map<InputDataNewsValidation,String> validMap = new HashMap<>();
	
	public DataNewsValidationException(String message) {
		super(message);
	}
	
	public DataNewsValidationException(Exception e) {
		super(e);
	}
	
	public DataNewsValidationException(String message, Exception e) {
		super(message, e);
	}
	
	public DataNewsValidationException(Map<InputDataNewsValidation,String> validMap, String message) {
		super(message);
		this.validMap = validMap;
	}

	public Map<InputDataNewsValidation,String> getValidMap() {
		return validMap;
	}

	public void setErrorList(Map<InputDataNewsValidation,String> validMap) {
		this.validMap = validMap;
	}
	

}
