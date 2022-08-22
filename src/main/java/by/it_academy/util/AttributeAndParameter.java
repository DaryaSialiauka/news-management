package by.it_academy.util;

public class AttributeAndParameter {

	public final static String ERROR_AUTHENTICATION = "ErrorAuthentication";
	public final static String ERROR_REGISTRATION = "ErrorRegistration";
	public final static String ERROR_ADD_EDIT_NEWS = "ErrorNews";
	
	public final static String ERROR_ADD_USER = "User not added";
	public final static String ERROR_ADD_NEWS = "News not added";
	
	public final static String NEWS_ERROR = "news_error";
	
	public final static String ERROR_CLASS_STYLE = "invalidclass";
	public final static String ERROR_STYLE = "is-invalid";
	public final static String STYLE = "_style";

	public final static String USER = "user";
	public final static String USER_ACTIV = "activ";
	public final static String USER_NOT_ACTIV = "not activ";

	public final static String ROLE = "role";

	public final static String USER_ID = "id";

	public final static String BODY = "body";
	public final static String REGISTRATION = "registration";
	public final static String SINGIN = "singin";
	public final static String NEWS = "news";
	public final static String VIEW_NEWS = "viewnews";
	public final static String ADD_NEWS = "addnews";

	public final static String EQUALS = "=";
	public final static String SEPARATOR = "&";

	public final static String USER_FIRSTNAME = "firstname";
	public final static String USER_LASTNAME = "lastname";
	public final static String USER_LOGIN = "login";
	public final static String USER_PASSWORD = "password";
	public final static String USER_PHONE = "phone";
	public final static String USER_EMAIL = "email";
	public final static String USER_DATEBIRTH = "datebirth";

	public final static String NEWS_TITLE = "title";
	public final static String NEWS_BRIEF = "brief";
	public final static String NEWS_CONTENT = "content";
	public final static String NEWS_DATE = "date_news";
	public final static String NEWS_ID = "id_news";

	public static String styleError(String error) {
		return error + STYLE;
	}

}
