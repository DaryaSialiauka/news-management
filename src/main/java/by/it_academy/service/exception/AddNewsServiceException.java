package by.it_academy.service.exception;

public class AddNewsServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AddNewsServiceException(String message) {
		super(message);
	}

	public AddNewsServiceException(Exception e) {
		super(e);
	}
	
	public AddNewsServiceException(String message, Exception e) {
		super(message,e);
	}
}
