package by.it_academy.service.exception;

public class AddUserServiseException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public AddUserServiseException(String message) {
		super(message);
	}
	
	public AddUserServiseException(Exception e) {
		super(e);
	}
	
	public AddUserServiseException(String message, Exception e) {
		super(message,e);
	}

}
