package by.it_academy.controller.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.it_academy.bean.User;
import by.it_academy.controller.Command;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.AddUserServiceException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.DateAndCalendar;
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
		String paramUser;
		User user;
		user = reqToUser(request);
		paramUser = addUserToResponse(user);

		try {
			idUser = provider.addUser(user);
			if (idUser == 0) {
				LOG.warn("User not added", user.toString());
				userNotAddedRedirect(request, response, paramUser);
			}
			addRoleSession(idUser, user, request);
			userAddedRedirect(request, response);

		} catch (AddUserServiceException e) {
			LOG.error(e);
			userNotAddedRedirect(request, response, paramUser);
		} catch (DataUserValidationException e) {
			LOG.error(e);
			userNotAddedRedirect(request, response, paramUser + errorListInput(e));
		}
	}

	private static String errorListInput(DataUserValidationException e) {

		String param = "";
		List<InputDataUserValidation> errorList;

		errorList = e.getErrorList();

		for (InputDataUserValidation error : errorList) {
			param += AttributeAndParameter.SEPARATOR + AttributeAndParameter.styleError(error.nameToLower())
					+ AttributeAndParameter.EQUALS + AttributeAndParameter.ERROR_STYLE;
			param += AttributeAndParameter.SEPARATOR + error.nameToLower() + AttributeAndParameter.EQUALS
					+ error.getTitle();
		}
		return param;

	}

	private static void userNotAddedRedirect(HttpServletRequest request, HttpServletResponse response, String param)
			throws ServletException, IOException {

		String path = JSPPageName.REGISTER_PAGE;

		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.BODY + AttributeAndParameter.EQUALS
				+ AttributeAndParameter.REGISTRATION;
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.ERROR_REGISTRATION
				+ AttributeAndParameter.EQUALS + AttributeAndParameter.ERROR_ADD_USER;

		path += param;

		request.getSession(true).setAttribute("url", JSPPageName.REGISTER_PAGE);

		response.sendRedirect(path);
	}

	private static void userAddedRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.sendRedirect(JSPPageName.LIST_NEWS);
	}

	private static User reqToUser(HttpServletRequest request) {
		String firstname = request.getParameter(AttributeAndParameter.USER_FIRSTNAME);
		String lastname = request.getParameter(AttributeAndParameter.USER_LASTNAME);
		String login = request.getParameter(AttributeAndParameter.USER_LOGIN);
		char[] password = request.getParameter(AttributeAndParameter.USER_PASSWORD).toCharArray();
		String phone = request.getParameter(AttributeAndParameter.USER_PHONE);
		String email = request.getParameter(AttributeAndParameter.USER_EMAIL);
		Calendar datebirth;

		try {
			datebirth = DateAndCalendar.strToCalendar(request.getParameter(AttributeAndParameter.USER_DATEBIRTH));
		} catch (ParseException e1) {
			LOG.error("Error date birth", request.getParameter(AttributeAndParameter.USER_DATEBIRTH));
			datebirth = new GregorianCalendar();
		}
		return new User(firstname, lastname, login, password, formatPhone(phone), email, datebirth, Role.USER);
	}

	private static String addUserToResponse(User user) {

		String path = "";
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.USER_FIRSTNAME + AttributeAndParameter.EQUALS
				+ user.getFirstname();
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.USER_LASTNAME + AttributeAndParameter.EQUALS
				+ user.getLastname();
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.USER_EMAIL + AttributeAndParameter.EQUALS
				+ user.getEmail();
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.USER_PHONE + AttributeAndParameter.EQUALS
				+ user.getPhone();
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.USER_DATEBIRTH + AttributeAndParameter.EQUALS
				+ DateAndCalendar.calendarToStr(user.getDatebirth());
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.USER_LOGIN + AttributeAndParameter.EQUALS
				+ user.getLogin();
		return path;

	}

	private static void addRoleSession(int idUser, User user, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_ACTIV);
		session.setAttribute(AttributeAndParameter.ROLE, user.getRole().getTitle());
		session.setAttribute(AttributeAndParameter.USER_ID, idUser);
	}

	private static String formatPhone(String phone) {
		return phone.replaceAll("[^0-9]", "");
	}

}
