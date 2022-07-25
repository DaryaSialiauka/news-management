package by.it_academy.dao.impl;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.FoundUserDAOException;

public class UserDAOImpl implements UserDAO {

	@Override
	public Role authentication(String login, String password) throws FoundUserDAOException {

		 throw new FoundUserDAOException("Not found user"); 
			/* return Role.ADMIN; */
	}

	public boolean addUser(User user) throws AddUserDAOException {
		
		 throw new AddUserDAOException("Not add user"); 
			/* return true; */
	}

}
