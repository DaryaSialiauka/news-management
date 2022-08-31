package by.it_academy.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.it_academy.bean.User;
import by.it_academy.controller.Command;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.AddUserServiceException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.DateUtil;
import by.it_academy.util.InputDataUserValidation;
import by.it_academy.util.JSPPageName;
import by.it_academy.util.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {

	private final static Logger LOG = LogManager.getLogger(DoRegistration.class);
	UserService provider = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser = 0;
		StringBuilder paramUser;
		User user;

		request.getSession(true).setAttribute(AttributeAndParameter.URL, JSPPageName.REGISTER_PAGE);

		user = reqToUser(request);
		if (user == null) {
			LOG.error("User is null");
			userNotAddedRedirect(request, response, new StringBuilder());
		}
		paramUser = addUserToResponse(user);

		try {
			idUser = provider.addUser(user);
			if (idUser == 0) {
				LOG.error("User not added", user.toString());
				userNotAddedRedirect(request, response, paramUser);
			}
			addRoleSession(idUser, user, request);
			userAddedRedirect(request, response);

		} catch (AddUserServiceException e) {
			LOG.error(e);
			userNotAddedRedirect(request, response, paramUser);
		} catch (DataUserValidationException e) {
			LOG.error(e);
			userNotAddedRedirect(request, response, paramUser.append(errorInput(e)));
		}
	}

	private static void userNotAddedRedirect(HttpServletRequest request, HttpServletResponse response,
			StringBuilder param) throws ServletException, IOException {

		StringBuilder url = new StringBuilder();

		url.append(JSPPageName.REGISTER_PAGE).append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.BODY)
				.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.REGISTRATION)
				.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.ERROR_REGISTRATION)
				.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.TRUE).append(param);

		response.sendRedirect(url.toString());
	}

	private static void userAddedRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.sendRedirect(JSPPageName.LIST_NEWS);
	}

	private static User reqToUser(HttpServletRequest request) {
		String firstname = request.getParameter(AttributeAndParameter.USER_FIRSTNAME).trim();
		String lastname = request.getParameter(AttributeAndParameter.USER_LASTNAME).trim();
		String login = request.getParameter(AttributeAndParameter.USER_LOGIN).trim();
		char[] password = request.getParameter(AttributeAndParameter.USER_PASSWORD).trim().toCharArray();
		String phone = request.getParameter(AttributeAndParameter.USER_PHONE).trim();
		String email = request.getParameter(AttributeAndParameter.USER_EMAIL).trim();
		LocalDate datebirth = LocalDate.now();

		try {
			datebirth = LocalDate.parse(request.getParameter(AttributeAndParameter.USER_DATEBIRTH));
		} catch (DateTimeParseException e1) {
			LOG.error("Error date birth", request.getParameter(AttributeAndParameter.USER_DATEBIRTH));
			// TODO
		}
		return new User(firstname, lastname, login, password, formatPhone(phone), email, datebirth, Role.USER);
	}

	private static StringBuilder addUserToResponse(User user) {

		StringBuilder param = new StringBuilder();
		param.append(AttributeAndParameter.SEPARATOR)
				.append(AttributeAndParameter.USER_FIRSTNAME + AttributeAndParameter.EQUALS)
				.append(user.getFirstname());
		param.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.USER_LASTNAME)
				.append(AttributeAndParameter.EQUALS).append(user.getLastname());
		param.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.USER_EMAIL)
				.append(AttributeAndParameter.EQUALS).append(user.getEmail());
		param.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.USER_PHONE)
				.append(AttributeAndParameter.EQUALS).append(user.getPhone());
		param.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.USER_DATEBIRTH)
				.append(AttributeAndParameter.EQUALS).append(DateUtil.localdateToStr(user.getDatebirth()));
		param.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.USER_LOGIN)
				.append(AttributeAndParameter.EQUALS).append(user.getLogin());
		return param;

	}

	private static void addRoleSession(int idUser, User user, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_ACTIV);
		session.setAttribute(AttributeAndParameter.ROLE, user.getRole().getTitle());
		session.setAttribute(AttributeAndParameter.USER_ID, idUser);
	}

	private static StringBuilder errorInput(DataUserValidationException e) {

		StringBuilder param = new StringBuilder();

		Iterator<InputDataUserValidation> entries = e.getErrorSet().iterator();
		while (entries.hasNext()) {
			InputDataUserValidation entry = entries.next();
			param.append(AttributeAndParameter.SEPARATOR)
					.append(AttributeAndParameter.styleError(entry.name().toLowerCase()))
					.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.ERROR_STYLE)
					.append(AttributeAndParameter.SEPARATOR).append(entry.name().toLowerCase())
					.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.TRUE);
		}

		return param;

	}

	private static String formatPhone(String phone) {
		return phone.replaceAll("[^0-9]", "");
	}

}
