package by.it_academy.service.impl;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.FoundUserDAOException;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.AddUserServiseException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.exception.FoundUserServiceException;

public class UserServiceImpl implements UserService {

	private static final String NULL_STR = null;
	private static final int MIN_LENGTH = 4;

	private UserDAO provider = DAOProvider.getInstance().getUserDAO();

	public Role authentication(String login, String password) throws ValidationAuthenticationException, FoundUserServiceException{
		Role role;
		if (validationLoginPassword(login, password)) {
			try {
				role = provider.authentication(login, password);
			} catch (FoundUserDAOException e) {
				throw new FoundUserServiceException(e.getMessage());
			}
		}
		else {
			throw new ValidationAuthenticationException("Error login or password");
		}
		return role;

	}

	private boolean validationLoginPassword(String login, String password) {
		boolean check = true;
		if ((login == NULL_STR) && (login.isEmpty())) {
			check = false;
		}

		if ((password == NULL_STR) && (password.isEmpty())) {
			check = false;
		}

		if (password.length() < MIN_LENGTH) {
			check = false;
		}
		return check;
	}

	@Override
	public boolean addUser(User user) throws AddUserServiseException {
		boolean add = false;
		try {
			add = provider.addUser(user);
		} catch (AddUserDAOException e) {
			throw new AddUserServiseException(e.getMessage());
		}
		return add;
	}

}
