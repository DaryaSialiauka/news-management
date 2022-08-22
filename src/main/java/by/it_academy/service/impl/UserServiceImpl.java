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
import by.it_academy.service.validation.UserDataValidation;
import by.it_academy.service.validation.ValidationProvider;
import by.it_academy.util.Role;
import by.it_academy.service.exception.FindUserServiceException;
import by.it_academy.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private final static UserDAO provider = DAOProvider.getInstance().getUserDAO();
	private final static UserDataValidation validProvider = ValidationProvider.getInstance().getUserDataValidation();

	public int authentication(String login, char[] password)
			throws ValidationAuthenticationException, ServiceException, FindUserServiceException {
		int idUser = 0;

		try {

			if (!validProvider.loginPasswordCheck(login, password)) {
				throw new ValidationAuthenticationException("Error login or password. Check login and password");
			}

			idUser = provider.authentication(login, password);

			if (idUser == 0) {
				throw new FindUserServiceException("User not found. Enter another login and password. Or register");
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (FindUserDAOException e) {
			throw new FindUserServiceException("User not found. Enter another login and password. Or register",e);
		}
		return idUser;

	}

	@Override
	public int addUser(User user) throws AddUserServiceException, DataUserValidationException {
		int idUser = 0;


		try {

			if (!validProvider.userDataCheck(user)) {
				throw new DataUserValidationException("Validation error. Check the entered data");
			}
			idUser = provider.addUser(user);
			
			if(idUser == 0) {
				throw new AddUserServiceException("");
			}
			
			return idUser;

		} catch (AddUserDAOException | DAOException e) {
			throw new AddUserServiceException(e.getMessage(),e);
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
