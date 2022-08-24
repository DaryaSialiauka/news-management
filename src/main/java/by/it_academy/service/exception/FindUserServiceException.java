package by.it_academy.service.exception;

public class FindUserServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FindUserServiceException(String message) {
		super(message);
	}

	public FindUserServiceException(Exception e) {
		super(e);
	}

	public FindUserServiceException(String message, Exception e) {
		super(message, e);
	}


}
