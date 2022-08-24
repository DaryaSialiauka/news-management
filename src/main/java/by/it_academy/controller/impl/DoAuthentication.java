package by.it_academy.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.it_academy.controller.Command;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.exception.FindUserServiceException;
import by.it_academy.service.exception.ServiceException;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.JSPPageName;
import by.it_academy.util.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoAuthentication implements Command {

	private final static Logger LOG = LogManager.getRootLogger();

	public UserService provider = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser;
		Role role;

		String login = request.getParameter(AttributeAndParameter.USER_LOGIN);
		char[] password = request.getParameter(AttributeAndParameter.USER_PASSWORD).toCharArray();

		HttpSession session = request.getSession(true);

		try {
			idUser = provider.authentication(login, password);

			role = provider.getRole(idUser);

			session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_ACTIV);
			session.setAttribute(AttributeAndParameter.ROLE, role.getTitle());
			session.setAttribute(AttributeAndParameter.USER_ID, idUser);

			String path = request.getContextPath() + "?";

			request.getSession(true).setAttribute("url", path);

			response.sendRedirect(path);

		} catch (ValidationAuthenticationException | FindUserServiceException | ServiceException e) {
			LOG.error(e);
			userNotAuthenticatedRedirect(request, response, e.getMessage());
		}
	}

	private static void userNotAuthenticatedRedirect(HttpServletRequest request, HttpServletResponse response,
			String message) throws IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_NOT_ACTIV);

		String path = JSPPageName.SING_IN_PAGE + AttributeAndParameter.SEPARATOR + AttributeAndParameter.BODY
				+ AttributeAndParameter.EQUALS + AttributeAndParameter.SINGIN + AttributeAndParameter.SEPARATOR
				+ AttributeAndParameter.ERROR_AUTHENTICATION + AttributeAndParameter.EQUALS + message
				+ AttributeAndParameter.SEPARATOR + AttributeAndParameter.ERROR_CLASS_STYLE
				+ AttributeAndParameter.EQUALS + AttributeAndParameter.ERROR_STYLE;

		response.sendRedirect(path);
	}

}