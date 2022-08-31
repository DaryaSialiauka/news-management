package by.it_academy.service.exception;

import java.util.Set;

import by.it_academy.util.InputDataUserValidation;

public class DataUserValidationException extends Exception{
	

	private static final long serialVersionUID = 1L;
	
	private Set<InputDataUserValidation> errorSet;
	
	public DataUserValidationException(String message) {
		super(message);
	}
	
	public DataUserValidationException(Exception e) {
		super(e);
	}
	
	public DataUserValidationException(String message, Exception e) {
		super(message, e);
	}
	
	public DataUserValidationException(Set<InputDataUserValidation> errorSet, String message) {
		super(message);
		this.errorSet = errorSet;
	}

	public Set<InputDataUserValidation> getErrorSet() {
		return errorSet;
	}

	public void setErrorSet(Set<InputDataUserValidation> errorSet) {
		this.errorSet = errorSet;
	}
	
	
	

}
