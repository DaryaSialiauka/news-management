package by.it_academy.service.validation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.it_academy.bean.User;
import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.ValidationUserDAO;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.util.InputDataUserValidation;

public class UserDataValidationImpl implements UserDataValidation {

	private final static String NULL_STR = null;
	private final static int MIN_LENGTH = 4;
	private final static int MAX_LENGTH = 10;
	private final static int MIN_YEAR = 18;
	private final static int LENGTH_PHONE = 12;
	private final static String EMAIL_REGEX = "^[a-zA-Z0-9]+" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]+)*" + "@" + "[a-zA-Z0-9]+"
			+ "((\\.|\\_|-{0,1})[a-zA-Z0-9]+)*" + "\\.[a-zA-Z]{2,}$";

	private final static String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{" + MIN_LENGTH + "," + MAX_LENGTH
			+ "})";

	private final static ValidationUserDAO provider = DAOProvider.getInstance().getValidationUserDAO();

	@Override
	public boolean userDataCheck(User user) throws DataUserValidationException {

		List<InputDataUserValidation> errorList = new ArrayList<InputDataUserValidation>();
		try {
			if (!checkLogin(user.getLogin())) {
				errorList.add(InputDataUserValidation.LOGIN_ERROR);
			}

			if (findLogin(user.getLogin())) {
				errorList.add(InputDataUserValidation.LOGIN_FOUND);
			}

			if (!checkEmail(user.getEmail())) {
				errorList.add(InputDataUserValidation.EMAIL_ERROR);
			}

			if (findEmail(user.getEmail())) {
				errorList.add(InputDataUserValidation.EMAIL_FOUND);
			}

			if (!checkPassword(user.getPassword())) {
				errorList.add(InputDataUserValidation.PASSWORD_ERROR);
			}

			if (!checkFirstname(user.getFirstname())) {
				errorList.add(InputDataUserValidation.FIRSTNAME_ERROR);
			}

			if (!checkLastname(user.getLastname())) {
				errorList.add(InputDataUserValidation.LASTNAME_ERROR);
			}

			if (!checkPhone(user.getPhone())) {
				errorList.add(InputDataUserValidation.PHONE_ERROR);
			}

			if (findPhone(user.getPhone())) {
				errorList.add(InputDataUserValidation.PHONE_FOUND);
			}

			if (!checkDatebirth(user.getDatebirth())) {
				errorList.add(InputDataUserValidation.DATEBIRTH_ERROR);
			}

			if (!minDatebirth(user.getDatebirth())) {
				errorList.add(InputDataUserValidation.DATEBIRTH_MIN);
			}
		} catch (DAOException e) {
			throw new DataUserValidationException("Error. Please try again later.");
		}

		if (!errorList.isEmpty()) {	
			throw new DataUserValidationException(errorList, "User not added");
		}

		return true;

	}

	@Override
	public boolean loginPasswordCheck(String login, char[] password) throws ValidationAuthenticationException {
		boolean check = true;

		check = checkPassword(password);

		check = checkLogin(login);

		if (!check) {
			throw new ValidationAuthenticationException("Error login or password");
		}
		return check;
	}

	private static boolean findLogin(String login) throws DAOException {
		return provider.findLogin(login);
	}

	private static boolean checkLogin(String login) {

		boolean check = true;
		if ((login == NULL_STR) && (login.isEmpty())) {
			check = false;
		}

		if (login.length() < MIN_LENGTH || login.length() > MAX_LENGTH) {
			check = false;
		}
		return check;
	}

	private static boolean checkPassword(char[] password) {

		boolean check = true;
		if (password.length == 0) {
			check = false;
		}

		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(PASSWORD_REGEX);
		matcher = pattern.matcher(String.valueOf(password));

		check = matcher.matches();

		return check;
	}

	private static boolean checkEmail(String email) {

		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(EMAIL_REGEX);
		matcher = pattern.matcher(email);

		return matcher.matches();
	}

	private static boolean findEmail(String email) throws DAOException {
		return provider.findEmail(email);
	}

	private static boolean checkFirstname(String firstname) {
		boolean check = true;

		if ((firstname == NULL_STR) && (firstname.isEmpty())) {
			check = false;
		}
		return check;
	}

	private static boolean checkLastname(String lastname) {
		boolean check = true;

		if ((lastname == NULL_STR) && (lastname.isEmpty())) {
			check = false;
		}
		return check;
	}

	private static boolean checkPhone(String phone) {
		boolean check = true;

		if ((phone == NULL_STR) && (phone.isEmpty())) {
			check = false;
		}

		if (phone.length() != LENGTH_PHONE) {
			check = false;
		}

		return check;
	}

	private static boolean findPhone(String phone) throws DAOException {
		return provider.findPhone(phone);
	}

	private static boolean checkDatebirth(Calendar datebirth) {

		return true;
	}

	private static boolean minDatebirth(Calendar datebirth) {

		Calendar now = new GregorianCalendar();

		now.add(Calendar.YEAR, (-1 * datebirth.get(Calendar.YEAR)));
		now.add(Calendar.MONTH, (-1 * datebirth.get(Calendar.MONTH)));
		now.add(Calendar.DAY_OF_MONTH, (-1 * datebirth.get(Calendar.DAY_OF_MONTH)));

		return (now.get(Calendar.YEAR) > MIN_YEAR);
	}

}
