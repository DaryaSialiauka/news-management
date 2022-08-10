package by.it_academy.dao;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindUserDAOException;

public interface UserDAO{

	public int authentication(String login, String password)  throws FindUserDAOException;
	
	public int addUser(User user) throws AddUserDAOException;
	
	public boolean findLogin(String login) throws DAOException;
	
	public boolean findEmail(String email) throws DAOException;
	
	public boolean findPhone(String phone) throws DAOException;
	
	public Role getRole(int id) throws DAOException;
}

