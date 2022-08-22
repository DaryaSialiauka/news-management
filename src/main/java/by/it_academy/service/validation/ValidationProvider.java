package by.it_academy.service.validation;

public class ValidationProvider {
	
	private final static ValidationProvider instance = new ValidationProvider();
	
	private final static UserDataValidation userDataValidation = new UserDataValidationImpl();
	
	private final static NewsDataValidation newsDataValidation = new NewsDataValidationImpl();
	
	private ValidationProvider() {
		
	}
	
	public static ValidationProvider getInstance() {
		return instance;
	}

	public UserDataValidation getUserDataValidation() {
		return userDataValidation;
	}
	
	public NewsDataValidation getNewsDataValidation() {
		return newsDataValidation;
	}
}
