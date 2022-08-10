package by.it_academy.service.impl;


import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindUserDAOException;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.AddUserServiseException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.validation.UserDataValidation;
import by.it_academy.service.validation.ValidationProvider;
import by.it_academy.service.exception.FindUserServiceException;
import by.it_academy.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private UserDAO provider = DAOProvider.getInstance().getUserDAO();
	private UserDataValidation validProvider = ValidationProvider.getInstance().getUserDataValidation();

	public int authentication(String login, String password)
			throws FindUserServiceException, ValidationAuthenticationException {
		int idUser = 0;

		try {

			if (!validProvider.loginPasswordCheck(login, password)) {
				throw new ValidationAuthenticationException("Error login or password");
			}
			idUser = provider.authentication(login, password);

		} catch (FindUserDAOException e) {
			throw new FindUserServiceException(e.getMessage());
		}
		return idUser;

	}

	@Override
	public int addUser(User user) throws AddUserServiseException, DataUserValidationException {
		int idUser = 0;

		user.setPhone(formatPhone(user.getPhone()));

		try {

			if (!validProvider.userDataCheck(user)) {
				throw new DataUserValidationException("Error validation");
			}
			idUser = provider.addUser(user);

		} catch (AddUserDAOException e) {
			throw new AddUserServiseException(e.getMessage());
		}
		return idUser;
	}

	private static String formatPhone(String phone) {

		return phone.replaceAll("[^0-9]", "");
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
