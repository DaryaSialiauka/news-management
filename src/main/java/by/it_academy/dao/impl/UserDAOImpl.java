package by.it_academy.dao.impl;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.NotFoundUserDAOException;

public class UserDAOImpl implements UserDAO {

	@Override
	public Role authentication(String login, String password) throws NotFoundUserDAOException {

		// throw new NotFoundUserDAOException("Not found user");
		return Role.ADMIN;
	}

	public boolean addUser(User user) throws AddUserDAOException {

		/* throw new AddUserDAOException("Not add user"); */
		return true;
	}

	@Override
	public boolean findLogin(String login) {
		return false;
	}

	@Override
	public boolean findEmail(String email) {
		return false;
	}

	@Override
	public boolean findPhone(String phone) {
		return false;
	}

}
