package by.it_academy.util;

public class Attribute {
	
	public static final String ERROR_AUTHENTICATION = "ErrorAuthentication";
	public static final String ERROR_REGISTRATION = "ErrorRegistration";
	public static final String ERROR_ADD_USER = "User not added";
	
	public static final String ERROR_CLASS_STYLE = "invalidclass";
	public static final String ERROR_STYLE = "is-invalid";
	public static final String STYLE = "_style";
	
	
	public static final String USER = "user";
	public static final String USER_ACTIV = "activ";
	public static final String USER_NOT_ACTIV = "not activ";
	
	public static final String ROLE = "role";
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_USER = "user";
	
	public static final String BODY = "body";
	public static final String REGISTRATION = "registration";
	public static final String SINGIN = "singin";
	public static final String RESULT_REG = "result_reg";
	
	public static final String EQUALS = "=";
	public static final String SEPARATOR = "&";
	
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	public static final String DATEBIRTH = "datebirth";
	
	
	public static String styleError(String error) {
		return error + STYLE;
	}

}
