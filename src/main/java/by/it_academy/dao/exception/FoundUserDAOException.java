package by.it_academy.dao.exception;

public class FoundUserDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public FoundUserDAOException(String message) {
		super(message);
	}

	public FoundUserDAOException(Exception e) {
		super(e);
	}

	public FoundUserDAOException(String message, Exception e) {
		super(message, e);
	}

}
