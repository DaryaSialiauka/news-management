package by.it_academy.service.exception;

import java.util.ArrayList;
import java.util.List;

import by.it_academy.util.InputDataUserValidation;

public class DataUserValidationException extends Exception{
	

	private static final long serialVersionUID = 1L;
	
	private List<InputDataUserValidation> errorList = new ArrayList<InputDataUserValidation>();
	
	public DataUserValidationException(String message) {
		super(message);
	}
	
	public DataUserValidationException(Exception e) {
		super(e);
	}
	
	public DataUserValidationException(String message, Exception e) {
		super(message, e);
	}
	
	public DataUserValidationException(List<InputDataUserValidation> errorList, String message) {
		super(message);
		this.errorList = errorList;
	}

	public List<InputDataUserValidation> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<InputDataUserValidation> errorList) {
		this.errorList = errorList;
	}
	
	
	

}
