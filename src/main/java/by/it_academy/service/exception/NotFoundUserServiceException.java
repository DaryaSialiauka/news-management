package by.it_academy.service.exception;

public class NotFoundUserServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NotFoundUserServiceException(String message) {
		super(message);
	}

	public NotFoundUserServiceException(Exception e) {
		super(e);
	}

	public NotFoundUserServiceException(String message, Exception e) {
		super(message, e);
	}


}
