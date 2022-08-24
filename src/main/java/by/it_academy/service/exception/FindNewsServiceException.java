package by.it_academy.service.exception;

public class FindNewsServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public FindNewsServiceException(String message) {
		super(message);
	}

	public FindNewsServiceException(Exception e) {
		super(e);
	}

	public FindNewsServiceException(String message, Exception e) {
		super(message, e);
	}
}
