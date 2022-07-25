package by.it_academy.controller.impl;

import java.io.IOException;

import by.it_academy.bean.Role;
import by.it_academy.controller.Command;
import by.it_academy.controller.JSPPageName;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.exception.FoundUserServiceException;
import by.it_academy.util.Attribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoAuthentication implements Command {

	public UserService provider = ServiceProvider.getInstance().getUserService();

	private static final String LOGIN_PARAM = "login";
	private static final String PASSWORD_PARAM = "password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Role role;
		String login = request.getParameter(LOGIN_PARAM);
		String password = request.getParameter(PASSWORD_PARAM);
		HttpSession session = request.getSession(true);

		try {
			role = provider.authentication(login, password);
			session.setAttribute(Attribute.USER, Attribute.USER_ACTIV);
			session.setAttribute(Attribute.ROLE, role);
			request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);
			
		} catch (ValidationAuthenticationException e) {
			session.setAttribute(Attribute.USER, Attribute.USER_NOT_ACTIV);
			request.setAttribute(Attribute.BODY, Attribute.SINGIN);
			request.setAttribute(Attribute.ERROR_AUTHENTICATION, e.getMessage());
			request.setAttribute(Attribute.ERROR_CLASS_STYLE, Attribute.ERROR_STYLE);
			request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);
		
		} catch (FoundUserServiceException e) {
			session.setAttribute(Attribute.USER, Attribute.USER_NOT_ACTIV);
			request.setAttribute(Attribute.BODY, Attribute.SINGIN);
			request.setAttribute(Attribute.ERROR_AUTHENTICATION, e.getMessage());
			request.setAttribute(Attribute.ERROR_CLASS_STYLE, Attribute.ERROR_STYLE);
			request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);
		}
	}

}
