package by.it_academy.service.impl;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.NotFoundUserDAOException;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.AddUserServiseException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.validation.UserDataValidation;
import by.it_academy.service.validation.ValidationProvider;
import by.it_academy.service.exception.NotFoundUserServiceException;

public class UserServiceImpl implements UserService {

	private UserDAO provider = DAOProvider.getInstance().getUserDAO();
	private UserDataValidation validProvider = ValidationProvider.getInstance().getUserDataValidation();

	public Role authentication(String login, String password) throws NotFoundUserServiceException, ValidationAuthenticationException {
		Role role = null;
		boolean check = false;
		
		check = validProvider.loginPasswordCheck(login, password);
		
		if (check) {
			try {
				role = provider.authentication(login, password);
			} catch (NotFoundUserDAOException e) {
				throw new NotFoundUserServiceException(e.getMessage());
			}
		}
		return role;

	}

	@Override
	public boolean addUser(User user) throws AddUserServiseException, DataUserValidationException {
		boolean add = false;
		boolean check = false;
		
		user.setPhone(formatPhone(user.getPhone()));
		check = validProvider.userDataCheck(user);
		
		try {
			if(check) {
				add = provider.addUser(user);
			}
			else {
				throw new DataUserValidationException("Error validation");
			}
		} catch (AddUserDAOException e) {
			throw new AddUserServiseException(e.getMessage());
		}
		return add;
	}
	
	private static String formatPhone(String phone) {
		
		return phone.replaceAll("[^0-9]", "");
	}

}
