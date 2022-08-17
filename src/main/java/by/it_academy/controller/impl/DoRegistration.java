package by.it_academy.controller.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

	UserService provider = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser = 0;
		String path;
		String paramUser;
		User user;

		user = reqToUser(request);
		paramUser = addUserToResponse(user);
		try {

			idUser = provider.addUser(user);
			if (idUser == 0) {
				userNotAddedRedirect(response, paramUser);
			}

			addRoleSession(idUser, user, request);
			path = request.getContextPath() + "?" + AttributeAndParameter.BODY + AttributeAndParameter.EQUALS + AttributeAndParameter.NEWS;
			response.sendRedirect(path);

		} catch (AddUserServiceException e) {

			userNotAddedRedirect(response, paramUser);

		} catch (DataUserValidationException e) {

			userNotAddedRedirect(response, paramUser + errorListInput(e));

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

	private static void userNotAddedRedirect(HttpServletResponse response, String param)
			throws ServletException, IOException {

		String path = JSPPageName.REGISTER_ERROR_PAGE;

		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.BODY + AttributeAndParameter.EQUALS
				+ AttributeAndParameter.REGISTRATION;
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.ERROR_REGISTRATION
				+ AttributeAndParameter.EQUALS + AttributeAndParameter.ERROR_ADD_USER;

		path += param;

		response.sendRedirect(path);
	}

	private static User reqToUser(HttpServletRequest request) {
		String firstname = request.getParameter(AttributeAndParameter.FIRSTNAME);
		String lastname = request.getParameter(AttributeAndParameter.LASTNAME);
		String login = request.getParameter(AttributeAndParameter.LOGIN);
		char[] password = request.getParameter(AttributeAndParameter.PASSWORD).toCharArray();
		String phone = request.getParameter(AttributeAndParameter.PHONE);
		String email = request.getParameter(AttributeAndParameter.EMAIL);
		Calendar datebirth;

		try {
			datebirth = DateAndCalendar.strToCalendar(request.getParameter(AttributeAndParameter.DATEBIRTH));
		} catch (ParseException e1) {
			datebirth = new GregorianCalendar();
		}
		return new User(firstname, lastname, login, password, formatPhone(phone), email, datebirth, Role.USER);
	}

	private static String addUserToResponse(User user) {

		String path = "";  
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.FIRSTNAME + AttributeAndParameter.EQUALS
				+ user.getFirstname();
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.LASTNAME + AttributeAndParameter.EQUALS
				+ user.getLastname();
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.EMAIL + AttributeAndParameter.EQUALS
				+ user.getEmail();
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.PHONE + AttributeAndParameter.EQUALS
				+ user.getPhone();
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.DATEBIRTH + AttributeAndParameter.EQUALS
				+ DateAndCalendar.calendarToStr(user.getDatebirth());
		path += AttributeAndParameter.SEPARATOR + AttributeAndParameter.LOGIN + AttributeAndParameter.EQUALS
				+ user.getLogin();
		return path;

	}

	private static void addRoleSession(int idUser, User user, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_ACTIV);
		session.setAttribute(AttributeAndParameter.ROLE, user.getRole().getTitle());
		session.setAttribute(AttributeAndParameter.ID, idUser);
	}
	
	private static String formatPhone(String phone) {

		return phone.replaceAll("[^0-9]", "");
	}

}
