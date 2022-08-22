package by.it_academy.service.validation;

import by.it_academy.bean.User;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;

public interface UserDataValidation {

	boolean userDataCheck(User user) throws DataUserValidationException;
	
	boolean loginPasswordCheck(String login, char[] password) throws ValidationAuthenticationException;
	
}
