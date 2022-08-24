package by.it_academy.dao;

import by.it_academy.bean.User;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindUserDAOException;
import by.it_academy.util.Role;

public interface UserDAO{

	int authentication(String login, char[] password)  throws DAOException, FindUserDAOException;
	
	int addUser(User user) throws DAOException, AddUserDAOException;
	
	User getUser(int id) throws FindUserDAOException, DAOException;
	
	Role getRole(int id) throws DAOException;
}

