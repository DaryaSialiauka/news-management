package by.it_academy.service.validation;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.ValidationUserDAO;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.util.InputDataUserValidation;

public class ValidationUserData {

	private final Map<InputDataUserValidation, String> validData;

	private ValidationUserData(ValidBuilder b) {
		this.validData = b.validData;
	}

	public Map<InputDataUserValidation, String> getValidData() {
		return this.validData;
	}

	public static class ValidBuilder {

		private final static ValidationUserDAO provider = DAOProvider.getInstance().getValidationUserDAO();

		private Map<InputDataUserValidation, String> validData = new HashMap<InputDataUserValidation, String>();

		public ValidBuilder firstnameValid(String firstname) {
			if (isEmptyString(firstname)) {
				validData.put(InputDataUserValidation.FIRSTNAME_ERROR, "Firstname is empty.");
			}
			return this;
		}

		public ValidBuilder lastnameValid(String lastname) {
			if (isEmptyString(lastname)) {
				validData.put(InputDataUserValidation.LASTNAME_ERROR, "Lastname is empty.");
			}
			return this;
		}

		public ValidBuilder emailValid(String email) throws DAOException {

			if (isEmptyString(email)) {
				validData.put(InputDataUserValidation.EMAIL_ERROR, "Email is empty.");
				return this;
			}

			if (!checkEmail(email)) {
				validData.put(InputDataUserValidation.EMAIL_ERROR, "Error email.");
			}

			if (findEmail(email)) {
				validData.put(InputDataUserValidation.EMAIL_ERROR, "Email found. Enter another email.");

			}

			return this;
		}

		private final static int LENGTH_PHONE = 12;

		public ValidBuilder phoneValid(String phone) throws DAOException {

			if (isEmptyString(phone)) {
				validData.put(InputDataUserValidation.PHONE_ERROR, "Phone is empty.");
				return this;
			}

			if (phone.length() != LENGTH_PHONE) {
				validData.put(InputDataUserValidation.PHONE_ERROR, "Phone length is out of range.");
			}

			if (findPhone(phone)) {
				validData.put(InputDataUserValidation.PHONE_ERROR, "Phone found. Enter another phone.");
			}
			return this;
		}

		public ValidBuilder datebirthValid(Calendar date) {

			if (Objects.isNull(date)) {
				validData.put(InputDataUserValidation.DATEBIRTH_ERROR, "Date is empty.");
				return this;
			}

			if (!minDatebirth(date)) {
				validData.put(InputDataUserValidation.DATEBIRTH_ERROR, "You must be over 18 years old.");
			}

			return this;
		}

		private final static int MIN_LOGIN_LENGTH = 4;
		private final static int MAX_LOGIN_LENGTH = 10;

		public ValidBuilder loginValid(String login) throws DAOException {

			if (isEmptyString(login)) {
				validData.put(InputDataUserValidation.LOGIN_ERROR, "Login is empty.");
				return this;
			}

			if (login.length() < MIN_LOGIN_LENGTH || login.length() > MAX_LOGIN_LENGTH) {
				validData.put(InputDataUserValidation.LOGIN_ERROR, "Login length is out of range.");
			}

			if (findLogin(login)) {
				validData.put(InputDataUserValidation.LOGIN_ERROR, "Login is busy. Enter another login.");
			}

			return this;
		}

		public ValidBuilder passwordValid(char[] password) {
			if (password.length == 0) {
				validData.put(InputDataUserValidation.PASSWORD_ERROR, "Password is empty.");
				return this;
			}
			
			if(!checkPassword(password)) {
				validData.put(InputDataUserValidation.PASSWORD_ERROR, "Password error.");
			}

			return this;
		}
		
		public ValidBuilder authenticationValid(String login, char[] password) {
			
			if (password.length == 0) {
				validData.put(InputDataUserValidation.PASSWORD_ERROR, "Password is empty.");
				return this;
			}
			
			if (isEmptyString(login)) {
				validData.put(InputDataUserValidation.LOGIN_ERROR, "Login is empty.");
				return this;
			}
			
			return this;
		}

		public ValidationUserData build() {
			return new ValidationUserData(this);
		}

		private final static int MIN_PASSWORD_LENGTH = 4;
		private final static int MAX_PASSWORD_LENGTH = 10;
		private final static String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{" + MIN_PASSWORD_LENGTH + ","
				+ MAX_PASSWORD_LENGTH + "})";

		private static boolean checkPassword(char[] password) {

			Pattern pattern;
			Matcher matcher;

			pattern = Pattern.compile(PASSWORD_REGEX);
			matcher = pattern.matcher(String.valueOf(password));
			return matcher.matches();
		}

		private final static String EMAIL_REGEX = "^[a-zA-Z0-9]+" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]+)*" + "@"
				+ "[a-zA-Z0-9]+" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]+)*" + "\\.[a-zA-Z]{2,}$";

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

		private final static String NULL_STR = null;

		private static boolean isEmptyString(String str) {
			if (str.isEmpty() || str == NULL_STR) {
				return true;
			}
			return false;
		}

		private static boolean findLogin(String login) throws DAOException {
			return provider.findLogin(login);
		}

		private static boolean findPhone(String phone) throws DAOException {
			return provider.findPhone(phone);
		}

		private final static int MIN_YEAR = 18;

		private static boolean minDatebirth(Calendar datebirth) {

			Calendar now = new GregorianCalendar();

			now.add(Calendar.YEAR, (-1 * datebirth.get(Calendar.YEAR)));
			now.add(Calendar.MONTH, (-1 * datebirth.get(Calendar.MONTH)));
			now.add(Calendar.DAY_OF_MONTH, (-1 * datebirth.get(Calendar.DAY_OF_MONTH)));

			return (now.get(Calendar.YEAR) > MIN_YEAR);
		}

	}

}
