package by.it_academy.service.exception;

public class FoundUserServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FoundUserServiceException(String message) {
		super(message);
	}

	public FoundUserServiceException(Exception e) {
		super(e);
	}

	public FoundUserServiceException(String message, Exception e) {
		super(message, e);
	}


}
