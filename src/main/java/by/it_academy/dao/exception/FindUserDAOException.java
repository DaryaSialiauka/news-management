package by.it_academy.dao.exception;

public class FindUserDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public FindUserDAOException(String message) {
		super(message);
	}

	public FindUserDAOException(Exception e) {
		super(e);
	}

	public FindUserDAOException(String message, Exception e) {
		super(message, e);
	}

}
