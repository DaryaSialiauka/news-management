package by.it_academy.dao.exception;

public class FindNewsDAOException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public FindNewsDAOException(String message) {
		super(message);
	}

	public FindNewsDAOException(Exception e) {
		super(e);
	}

	public FindNewsDAOException(String message, Exception e) {
		super(message, e);
	}

}
