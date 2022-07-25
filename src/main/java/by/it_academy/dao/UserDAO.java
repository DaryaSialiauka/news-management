package by.it_academy.dao;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.FoundUserDAOException;

public interface UserDAO{

	public Role authentication(String login, String password)  throws FoundUserDAOException;
	
	public boolean addUser(User user) throws AddUserDAOException;
}

