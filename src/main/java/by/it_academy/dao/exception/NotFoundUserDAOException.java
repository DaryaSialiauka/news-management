package by.it_academy.dao.exception;

public class NotFoundUserDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundUserDAOException(String message) {
		super(message);
	}

	public NotFoundUserDAOException(Exception e) {
		super(e);
	}

	public NotFoundUserDAOException(String message, Exception e) {
		super(message, e);
	}

}
