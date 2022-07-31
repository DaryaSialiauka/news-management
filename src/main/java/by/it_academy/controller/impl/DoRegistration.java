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

public class DoRegistration implements Command {

	UserService provider = ServiceProvider.getInstance().getUserService();

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean add = false;
		String path;

		User user = reqToUser(request);

		try {
			add = provider.addUser(user);
			if (add) {
				path = request.getContextPath() + "?";
				response.sendRedirect(path);
			} else {

				userNotAdded(userToReq(user), response);
			}
		} catch (AddUserServiseException e) {

			userNotAdded(userToReq(user), response);

		} catch (DataUserValidationException e) {

			userNotAdded(userToReq(user) + errorListInput(e), response);

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

	private static String userToReq(User user) {
		String param = "";

		param += Attribute.SEPARATOR + Attribute.FIRSTNAME + Attribute.EQUALS + user.getFirstname();
		param += Attribute.SEPARATOR + Attribute.LASTNAME + Attribute.EQUALS + user.getLastname();
		param += Attribute.SEPARATOR + Attribute.LOGIN + Attribute.EQUALS + user.getLogin();
		param += Attribute.SEPARATOR + Attribute.PHONE + Attribute.EQUALS + user.getPhone();
		param += Attribute.SEPARATOR + Attribute.EMAIL + Attribute.EQUALS + user.getEmail();
		param += Attribute.SEPARATOR + Attribute.DATEBIRTH + Attribute.EQUALS + calendarToStr(user.getDatebirth());

		return param;
	}

	private static Calendar strToCalendar(String date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		cal.setTime(sdf.parse(date));
		return cal;
	}

	private static String calendarToStr(Calendar date) {
		String dateStr = "";
		dateStr += date.get(Calendar.YEAR) + "-" + (new DecimalFormat( "00" ).format(date.get(Calendar.MONTH) + 1)) + "-"
				+ date.get(Calendar.DAY_OF_MONTH);
		return dateStr;
	}
}
