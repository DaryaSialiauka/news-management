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

		String login = request.getParameter(AttributeAndParameter.USER_LOGIN).trim();
		char[] password = request.getParameter(AttributeAndParameter.USER_PASSWORD).trim().toCharArray();

		HttpSession session = request.getSession(true);

		session.setAttribute(AttributeAndParameter.URL, JSPPageName.SING_IN_PAGE);

		try {
			idUser = provider.authentication(login, password);
			
			if(idUser == 0) {
				LOG.error("Error authentication.");
				userNotAuthenticatedRedirect(request, response, "Error authentication.");
			}

			role = provider.getRole(idUser);

			session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_ACTIV);
			session.setAttribute(AttributeAndParameter.ROLE, role.getTitle());
			session.setAttribute(AttributeAndParameter.USER_ID, idUser);

			response.sendRedirect(JSPPageName.LIST_NEWS);

		} catch (ValidationAuthenticationException | FindUserServiceException | ServiceException e) {
			LOG.error(e);
			userNotAuthenticatedRedirect(request, response, e.getMessage());
		}
	}

	private static void userNotAuthenticatedRedirect(HttpServletRequest request, HttpServletResponse response,
			String message) throws IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_NOT_ACTIV);

		StringBuilder url = new StringBuilder();
		url.append(JSPPageName.SING_IN_PAGE).append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.BODY)
				.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.SINGIN)
				.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.ERROR_AUTHENTICATION)
				.append(AttributeAndParameter.EQUALS).append(message).append(AttributeAndParameter.SEPARATOR)
				.append(AttributeAndParameter.ERROR_CLASS_STYLE).append(AttributeAndParameter.EQUALS)
				.append(AttributeAndParameter.ERROR_STYLE);

		response.sendRedirect(url.toString());
	}

}