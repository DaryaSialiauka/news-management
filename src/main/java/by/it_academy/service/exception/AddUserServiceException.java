package by.it_academy.service.exception;

public class AddUserServiceException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public AddUserServiceException(String message) {
		super(message);
	}
	
	public AddUserServiceException(Exception e) {
		super(e);
	}
	
	public AddUserServiceException(String message, Exception e) {
		super(message,e);
	}

}
