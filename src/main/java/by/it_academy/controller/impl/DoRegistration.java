package by.it_academy.controller.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.controller.Command;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.AddUserServiseException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.util.Attribute;
import by.it_academy.util.InputDataUserValidation;
import by.it_academy.util.JSPPageName;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {

	UserService provider = ServiceProvider.getInstance().getUserService();

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean add = false;
		String path;

		User user = reqToUser(request);
		
		try {
			addSessionUser(user,request);
			add = provider.addUser(user);
			if (add) {
				delSessionUser(request);
				path = request.getContextPath() + "?";
				response.sendRedirect(path);
			} else {
				userNotAdded("", response);
			}
		} catch (AddUserServiseException e) {

			userNotAdded("", response);

		} catch (DataUserValidationException e) {

			userNotAdded(errorListInput(e), response);

		}
	}

	private static String errorListInput(DataUserValidationException e) {

		String param = "";
		List<InputDataUserValidation> errorList;

		errorList = e.getErrorList();

		for (InputDataUserValidation error : errorList) {
			param += Attribute.SEPARATOR + Attribute.styleError(error.nameToLower()) + Attribute.EQUALS
					+ Attribute.ERROR_STYLE;
			param += Attribute.SEPARATOR + error.nameToLower() + Attribute.EQUALS + error.getTitle();
		}
		return param;

	}

	private static void userNotAdded(String param, HttpServletResponse response) throws ServletException, IOException {

		String path = JSPPageName.REGISTER_ERROR_PAGE + param;

		path += Attribute.SEPARATOR + Attribute.BODY + Attribute.EQUALS + Attribute.REGISTRATION;
		path += Attribute.SEPARATOR + Attribute.ERROR_REGISTRATION + Attribute.EQUALS + Attribute.ERROR_ADD_USER;

		response.sendRedirect(path);
	}

	private static User reqToUser(HttpServletRequest request) {
		String firstname = request.getParameter(Attribute.FIRSTNAME);
		String lastname = request.getParameter(Attribute.LASTNAME);
		String login = request.getParameter(Attribute.LOGIN);
		String password = request.getParameter(Attribute.PASSWORD);
		String phone = request.getParameter(Attribute.PHONE);
		String email = request.getParameter(Attribute.EMAIL);
		Calendar datebirth;

		try {
			datebirth = strToCalendar(request.getParameter(Attribute.DATEBIRTH));
		} catch (ParseException e1) {
			datebirth = new GregorianCalendar();
		}
		return new User(firstname, lastname, login, password, phone, email, datebirth, Role.USER);
	}
 
	
	private static Calendar strToCalendar(String date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		cal.setTime(sdf.parse(date));
		return cal;
	}
	 
	private static void addSessionUser(User user, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("userbean", user);
	}
	
	private static void delSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.removeAttribute("userbean"); 
	}

}
