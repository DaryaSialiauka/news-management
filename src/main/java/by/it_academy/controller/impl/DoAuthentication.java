package by.it_academy.controller.impl;

import java.io.IOException;

import by.it_academy.bean.Role;
import by.it_academy.controller.Command;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.service.exception.NotFoundUserServiceException;
import by.it_academy.util.Attribute;
import by.it_academy.util.JSPPageName;
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

			String path = request.getContextPath() + "?";
			response.sendRedirect(path);

		} catch (ValidationAuthenticationException | NotFoundUserServiceException e) {
			session.setAttribute(Attribute.USER, Attribute.USER_NOT_ACTIV);

			String path = JSPPageName.SING_IN_ERROR_PAGE + Attribute.SEPARATOR + Attribute.BODY + Attribute.EQUALS
					+ Attribute.SINGIN + Attribute.SEPARATOR + Attribute.ERROR_AUTHENTICATION + Attribute.EQUALS
					+ e.getMessage() + Attribute.SEPARATOR + Attribute.ERROR_CLASS_STYLE + Attribute.EQUALS
					+ Attribute.ERROR_STYLE;

			response.sendRedirect(path);

		}
	}

}
