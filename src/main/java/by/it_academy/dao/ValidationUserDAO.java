package by.it_academy.dao;

import by.it_academy.dao.exception.DAOException;

public interface ValidationUserDAO {
	
	boolean findLogin(String login) throws DAOException;
	
	boolean findEmail(String email) throws DAOException;
	
	boolean findPhone(String phone) throws DAOException;
	
	
}
