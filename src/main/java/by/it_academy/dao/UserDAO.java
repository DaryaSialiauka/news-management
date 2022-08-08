package by.it_academy.dao;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.NotFoundUserDAOException;

public interface UserDAO{

	public Role authentication(String login, String password)  throws NotFoundUserDAOException;
	
	public boolean addUser(User user) throws AddUserDAOException;
	
	public boolean findLogin(String login) throws DAOException;
	
	public boolean findEmail(String email) throws DAOException;
	
	public boolean findPhone(String phone) throws DAOException;
}

