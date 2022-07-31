package by.it_academy.service;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.service.exception.AddUserServiseException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.exception.NotFoundUserServiceException;

public interface UserService {
	
	public Role authentication(String login, String password) throws ValidationAuthenticationException, NotFoundUserServiceException;
	
	public boolean addUser(User user) throws AddUserServiseException, DataUserValidationException;
	
	

}
