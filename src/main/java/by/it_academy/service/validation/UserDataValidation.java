package by.it_academy.service.validation;

import by.it_academy.bean.User;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;

public interface UserDataValidation {

	public boolean userDataCheck(User user) throws DataUserValidationException;
	
	public boolean loginPasswordCheck(String login, String password) throws ValidationAuthenticationException;
	
}
