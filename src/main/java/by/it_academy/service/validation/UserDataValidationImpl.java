package by.it_academy.service.validation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.it_academy.bean.User;
import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.UserDAO;
import by.it_academy.service.exception.DataUserValidationException;
import by.it_academy.service.exception.ValidationAuthenticationException;
import by.it_academy.util.InputDataUserValidation;

public class UserDataValidationImpl implements UserDataValidation {

	private static final String NULL_STR = null;
	private static final int MIN_LENGTH = 4;
	private static final int MAX_LENGTH = 10;
	private static final int MIN_YEAR = 18;
	private static final int LENGTH_PHONE = 12;
	private static final String REGEX = "^[a-zA-Z0-9]+" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]+)*" + "@" + "[a-zA-Z0-9]+"
			+ "((\\.|\\_|-{0,1})[a-zA-Z0-9]+)*" + "\\.[a-zA-Z]{2,}$";

	private final static UserDAO provider = DAOProvider.getInstance().getUserDAO();

	@Override
	public boolean userDataCheck(User user) throws DataUserValidationException {

		List<InputDataUserValidation> errorList = new ArrayList<InputDataUserValidation>();
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

		if (!errorList.isEmpty()) {
			throw new DataUserValidationException(errorList, "User not added");
		}
		return true;

	}

	@Override
	public boolean loginPasswordCheck(String login, String password) throws ValidationAuthenticationException {
		boolean check = true;

		check = checkPassword(password);

		check = checkLogin(login);

		if (!check) {
			throw new ValidationAuthenticationException("Error login or password");
		}
		return check;
	}

	private static boolean findLogin(String login) {
		boolean find = true;
		find = provider.findLogin(login);
		return find;
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

	private static boolean checkPassword(String password) {

		boolean check = true;
		if ((password == NULL_STR) && (password.isEmpty())) {
			check = false;
		}

		if (password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
			check = false;
		}
		return check;
	}

	private static boolean checkEmail(String email) {

		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(REGEX);
		matcher = pattern.matcher(email);

		return matcher.matches();
	}

	private static boolean findEmail(String email) {
		boolean find = true;
		find = provider.findEmail(email);
		return find;
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

	private static boolean findPhone(String phone) {
		boolean find = true;
		find = provider.findPhone(phone);
		return find;
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