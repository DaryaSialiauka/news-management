package by.it_academy.controller.impl;

import java.io.IOException;

import by.it_academy.bean.Role;
import by.it_academy.controller.Command;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.exception.FindUserServiceException;
import by.it_academy.service.exception.ServiceException;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoAuthentication implements Command {

	public UserService provider = ServiceProvider.getInstance().getUserService();



	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser;
		Role role;
		String login = request.getParameter(AttributeAndParameter.LOGIN);
		String password = request.getParameter(AttributeAndParameter.PASSWORD);
		HttpSession session = request.getSession(true);
		session.removeAttribute(AttributeAndParameter.USER_BEAN);

		try {
			idUser = provider.authentication(login, password);
			
			role = provider.getRole(idUser);
			
			session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_ACTIV);
			session.setAttribute(AttributeAndParameter.ROLE, role);
			session.setAttribute(AttributeAndParameter.ID, idUser);

			String path = request.getContextPath() + "?";
			response.sendRedirect(path);

		} catch (ValidationAuthenticationException | FindUserServiceException | ServiceException e) {
			userNotAuthenticatedRedirect(request, response, e);
		}
	}
	
	private static void userNotAuthenticatedRedirect(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_NOT_ACTIV);

		String path = JSPPageName.SING_IN_ERROR_PAGE + AttributeAndParameter.SEPARATOR + AttributeAndParameter.BODY + AttributeAndParameter.EQUALS
				+ AttributeAndParameter.SINGIN + AttributeAndParameter.SEPARATOR + AttributeAndParameter.ERROR_AUTHENTICATION + AttributeAndParameter.EQUALS
				+ e.getMessage() + AttributeAndParameter.SEPARATOR + AttributeAndParameter.ERROR_CLASS_STYLE + AttributeAndParameter.EQUALS
				+ AttributeAndParameter.ERROR_STYLE;

		response.sendRedirect(path);
	}

}
