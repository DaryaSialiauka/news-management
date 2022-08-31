package by.it_academy.service.validation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.ValidationUserDAO;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.util.InputDataUserValidation;

public class ValidationUserData {

	private final Set<InputDataUserValidation> validData;

	private ValidationUserData(ValidBuilder b) {
		this.validData = b.validData;
	}

	public Set<InputDataUserValidation> getValidData() {
		return this.validData;
	}

	public static class ValidBuilder {

		private final static ValidationUserDAO provider = DAOProvider.getInstance().getValidationUserDAO();

		private Set<InputDataUserValidation> validData = new HashSet<InputDataUserValidation>();

		public ValidBuilder firstnameValid(String firstname) {
			if (isEmptyString(firstname)) {
				validData.add(InputDataUserValidation.FIRSTNAME_ERROR);
			}
			return this;
		}

		public ValidBuilder lastnameValid(String lastname) {
			if (isEmptyString(lastname)) {
				validData.add(InputDataUserValidation.LASTNAME_ERROR);
			}
			return this;
		}

		public ValidBuilder emailValid(String email) throws DAOException {

			if (isEmptyString(email)) {
				validData.add(InputDataUserValidation.EMAIL_ERROR);
				return this;
			}

			if (!checkEmail(email)) {
				validData.add(InputDataUserValidation.EMAIL_ERROR);
			}

			if (findEmail(email)) {
				validData.add(InputDataUserValidation.EMAIL_FOUND);

			}

			return this;
		}

		private final static int LENGTH_PHONE = 12;

		public ValidBuilder phoneValid(String phone) throws DAOException {

			if (isEmptyString(phone)) {
				validData.add(InputDataUserValidation.PHONE_ERROR);
				return this;
			}

			if (phone.length() != LENGTH_PHONE) {
				validData.add(InputDataUserValidation.PHONE_ERROR);
			}

			if (findPhone(phone)) {
				validData.add(InputDataUserValidation.PHONE_FOUND);
			}
			return this;
		}

		public ValidBuilder datebirthValid(LocalDate date) {

			if (Objects.isNull(date)) {
				validData.add(InputDataUserValidation.DATEBIRTH_ERROR);
				return this;
			}

			if (!minDatebirth(date)) {
				validData.add(InputDataUserValidation.DATEBIRTH_MIN);
			}

			return this;
		}

		private final static int MIN_LOGIN_LENGTH = 4;
		private final static int MAX_LOGIN_LENGTH = 10;

		public ValidBuilder loginValid(String login) throws DAOException {

			if (isEmptyString(login)) {
				validData.add(InputDataUserValidation.LOGIN_ERROR);
				return this;
			}

			if (login.length() < MIN_LOGIN_LENGTH || login.length() > MAX_LOGIN_LENGTH) {
				validData.add(InputDataUserValidation.LOGIN_ERROR);
			}

			if (findLogin(login)) {
				validData.add(InputDataUserValidation.LOGIN_FOUND);
			}

			return this;
		}

		public ValidBuilder passwordValid(char[] password) {
			if (password.length == 0) {
				validData.add(InputDataUserValidation.PASSWORD_ERROR);
				return this;
			}
			
			if(!checkPassword(password)) {
				validData.add(InputDataUserValidation.PASSWORD_ERROR);
			}

			return this;
		}
		
		public ValidBuilder authenticationValid(String login, char[] password) {
			
			if (password.length == 0) {
				validData.add(InputDataUserValidation.PASSWORD_ERROR);
				return this;
			}
			
			if (isEmptyString(login)) {
				validData.add(InputDataUserValidation.LOGIN_ERROR);
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

		private static boolean minDatebirth(LocalDate datebirth) {

			LocalDate dateMin = LocalDate.now();
			
			dateMin = dateMin.minusYears(MIN_YEAR);
			
			System.out.println(datebirth.isBefore(dateMin));
			
			return datebirth.isBefore(dateMin);
		}

	}

}
