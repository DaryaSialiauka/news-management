package by.it_academy.dao;

import by.it_academy.dao.impl.NewsDAOImpl;
import by.it_academy.dao.impl.UserDAOImpl;
import by.it_academy.dao.impl.ValidationUserDAOImpl;

public class DAOProvider {

	private final static DAOProvider instance = new DAOProvider();
	
	private final static UserDAO userDAO = new UserDAOImpl();
	private final static ValidationUserDAO validationUserDAO = new ValidationUserDAOImpl();
	private final static NewsDAO newsDAO = new NewsDAOImpl();
	
	private DAOProvider() {
		
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public ValidationUserDAO getValidationUserDAO() {
		return validationUserDAO;
	}
	
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}
}
