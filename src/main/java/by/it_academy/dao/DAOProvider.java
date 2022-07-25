package by.it_academy.dao;

import by.it_academy.dao.impl.UserDAOImpl;

public class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	
	private UserDAO userDAO = new UserDAOImpl();
	
	private DAOProvider() {
		
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
}
