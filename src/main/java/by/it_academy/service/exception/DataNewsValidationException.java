package by.it_academy.service.exception;

import java.util.ArrayList;
import java.util.List;

import by.it_academy.util.InputDataNewsValidation;

public class DataNewsValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
private List<InputDataNewsValidation> errorList = new ArrayList<InputDataNewsValidation>();
	
	public DataNewsValidationException(String message) {
		super(message);
	}
	
	public DataNewsValidationException(Exception e) {
		super(e);
	}
	
	public DataNewsValidationException(String message, Exception e) {
		super(message, e);
	}
	
	public DataNewsValidationException(List<InputDataNewsValidation> errorList, String message) {
		super(message);
		this.errorList = errorList;
	}

	public List<InputDataNewsValidation> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<InputDataNewsValidation> errorList) {
		this.errorList = errorList;
	}
	

}
