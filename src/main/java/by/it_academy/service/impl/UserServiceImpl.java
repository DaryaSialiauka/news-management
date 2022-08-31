package by.it_academy.service.impl;

import by.it_academy.bean.User;
import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindUserDAOException;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.AddUserServiceException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.validation.ValidationUserData;
import by.it_academy.util.Role;
import by.it_academy.service.exception.FindUserServiceException;
import by.it_academy.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private final static UserDAO provider = DAOProvider.getInstance().getUserDAO();
	
	public int authentication(String login, char[] password)
			throws ValidationAuthenticationException, ServiceException, FindUserServiceException {
		int idUser = 0;

		try {
			
			ValidationUserData validUser;
			ValidationUserData.ValidBuilder valid = new ValidationUserData.ValidBuilder();

			validUser = valid.authenticationValid(login, password).build();

			if (!validUser.getValidData().isEmpty()) {
				throw new ValidationAuthenticationException("Error login or password");
			}

			idUser = provider.authentication(login, password);

			if (idUser == 0) {
				throw new FindUserServiceException("User not found. idUser = " + idUser);
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (FindUserDAOException e) {
			throw new FindUserServiceException(e.getMessage(), e);
		}
		return idUser;

	}

	@Override
	public int addUser(User user) throws AddUserServiceException, DataUserValidationException {
		int idUser = 0;

		try {

			ValidationUserData validUser;
			ValidationUserData.ValidBuilder valid = new ValidationUserData.ValidBuilder();

			validUser = valid.firstnameValid(user.getFirstname()).lastnameValid(user.getLastname())
					.datebirthValid(user.getDatebirth()).emailValid(user.getEmail()).phoneValid(user.getPhone())
					.loginValid(user.getLogin()).passwordValid(user.getPassword()).build();

			if (!validUser.getValidData().isEmpty()) {
				throw new DataUserValidationException(validUser.getValidData(), "User not valid");
			}

			idUser = provider.addUser(user);

			if (idUser == 0) {
				throw new AddUserServiceException("");
			}

			return idUser;

		} catch (AddUserDAOException | DAOException e) {
			throw new AddUserServiceException(e.getMessage(), e);
		}

	}

	@Override
	public Role getRole(int id) throws ServiceException {

		Role role;

		try {
			role = provider.getRole(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return role;
	}

}
