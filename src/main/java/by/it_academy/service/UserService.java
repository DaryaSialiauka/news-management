package by.it_academy.service;

import by.it_academy.bean.User;
import by.it_academy.service.exception.AddUserServiceException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.util.Role;
import by.it_academy.service.exception.FindUserServiceException;
import by.it_academy.service.exception.ServiceException;

public interface UserService {
	
	public int authentication(String login, char[] password) throws ValidationAuthenticationException, ServiceException, FindUserServiceException;
	
	public int addUser(User user) throws AddUserServiceException, DataUserValidationException;
	
	public Role getRole(int id) throws ServiceException;

}
