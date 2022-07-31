package by.it_academy.util;

public enum InputDataUserValidation {

	LOGIN_FOUND("Login was found"),
	LOGIN_ERROR("Error login"),
	PASSWORD_ERROR("Error password"),
	FIRSTNAME_ERROR("Error firstname"),
	LASTNAME_ERROR("Error lastname"),
	EMAIL_FOUND("Email was found"),
	EMAIL_ERROR("Error email"),
	PHONE_ERROR("Error phone"),
	PHONE_FOUND("Phone was found"),
	DATEBIRTH_ERROR("Error datebirth"),
	DATEBIRTH_MIN("You must be over 18 years old");
	
	private String title;

	InputDataUserValidation(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public String nameToLower() {
		return this.name().toLowerCase();
	}

	
	
}
