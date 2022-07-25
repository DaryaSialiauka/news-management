package by.it_academy.service.exception;

public class ValidationAuthenticationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ValidationAuthenticationException(String message) {
		super(message);
	}
	
	public ValidationAuthenticationException(Exception e) {
		super(e);
	}
	
	public ValidationAuthenticationException(String message, Exception e) {
		super(message, e);
	}

}
