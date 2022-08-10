package by.it_academy.util;

public class AttributeAndParameter {
	
	public final static String ERROR_AUTHENTICATION = "ErrorAuthentication";
	public final static String ERROR_REGISTRATION = "ErrorRegistration";
	public final static String ERROR_ADD_USER = "User not added";
	
	public final static String ERROR_CLASS_STYLE = "invalidclass";
	public final static String ERROR_STYLE = "is-invalid";
	public final static String STYLE = "_style";
	
	
	public final static String USER = "user";
	public final static String USER_ACTIV = "activ";
	public final static String USER_NOT_ACTIV = "not activ";
	
	public final static String USER_BEAN = "userbean";
	
	public final static String ROLE = "role";
	public final static String ROLE_ADMIN = "admin";
	public final static String ROLE_USER = "user";
	
	public final static String ID = "id";
	
	public final static String BODY = "body";
	public final static String REGISTRATION = "registration";
	public final static String SINGIN = "singin";
	public final static String RESULT_REG = "result_reg";
	
	public final static String EQUALS = "=";
	public final static String SEPARATOR = "&";
	
	public final static String FIRSTNAME = "firstname";
	public final static String LASTNAME = "lastname";
	public final static String LOGIN = "login";
	public final static String PASSWORD = "password";
	public final static String PHONE = "phone";
	public final static String EMAIL = "email";
	public final static String DATEBIRTH = "datebirth";
	
	
	public static String styleError(String error) {
		return error + STYLE;
	}

}
