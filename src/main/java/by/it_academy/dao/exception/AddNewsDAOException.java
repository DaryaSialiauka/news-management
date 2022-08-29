package by.it_academy.dao.exception;

public class AddNewsDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public AddNewsDAOException(String message) {
		super(message);
	}

	public AddNewsDAOException(Exception e) {
		super(e);
	}

	public AddNewsDAOException(String message, Exception e) {
		super(message, e);
	}

}