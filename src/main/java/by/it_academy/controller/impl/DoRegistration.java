package by.it_academy.controller.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.controller.Command;
import by.it_academy.controller.JSPPageName;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.AddUserServiseException;
import by.it_academy.util.Attribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	UserService provider = ServiceProvider.getInstance().getUserService();
	private static final String FIRSTNAME = "firstname";
	private static final String LASTNAME = "lastname";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String PHONE = "phone";
	private static final String EMAIL = "email";
	private static final String DATEBIRTH = "datebirth";

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean add = false;
		User user = reqToUser(request);

		try {
			add = provider.addUser(user);
		} catch (AddUserServiseException e) {
			
			request.setAttribute(Attribute.ERROR_REGISTRATION, e.getMessage());
			request.setAttribute(Attribute.BODY, Attribute.REGISTRATION);
			request.setAttribute(Attribute.ERROR_CLASS_STYLE, Attribute.ERROR_STYLE);
			request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);
		}
		if (add) {
			request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);
		}
		else {
			request.setAttribute(Attribute.ERROR_REGISTRATION, "Not add user");
			request.setAttribute(Attribute.BODY, Attribute.REGISTRATION);
			request.setAttribute(Attribute.ERROR_CLASS_STYLE, Attribute.ERROR_STYLE);
			request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);
		}

	}

	private Calendar strToCalendar(String date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		cal.setTime(sdf.parse(date));
		return cal;
	}

	private User reqToUser(HttpServletRequest request) {
		String firstname = request.getParameter(FIRSTNAME);
		String lastname = request.getParameter(LASTNAME);
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String phone = request.getParameter(PHONE);
		String email = request.getParameter(EMAIL);
		Calendar datebirth;

		try {
			datebirth = strToCalendar(request.getParameter(DATEBIRTH));
		} catch (ParseException e1) {
			datebirth = new GregorianCalendar();
		}
		return new User(firstname, lastname, login, password, phone, email, datebirth, Role.USER);
	}

}
