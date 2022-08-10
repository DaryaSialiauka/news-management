package by.it_academy.service;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.service.exception.AddUserServiseException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.exception.FindUserServiceException;
import by.it_academy.service.exception.ServiceException;

public interface UserService {
	
	public int authentication(String login, String password) throws ValidationAuthenticationException, FindUserServiceException;
	
	public int addUser(User user) throws AddUserServiseException, DataUserValidationException;
	
	public Role getRole(int id) throws ServiceException;

}
