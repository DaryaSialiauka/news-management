package by.it_academy.dao.exception;

public class AddUserDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public AddUserDAOException(String message) {
		super(message);
	}

	public AddUserDAOException(Exception e) {
		super(e);
	}

	public AddUserDAOException(String message, Exception e) {
		super(message, e);
	}

}
