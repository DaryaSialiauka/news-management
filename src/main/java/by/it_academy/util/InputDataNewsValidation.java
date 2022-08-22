package by.it_academy.util;

public enum InputDataNewsValidation {
	
	TITLE_ERROR("Error title"),
	BRIEF_ERROR("Error brief"),
	CONTENT_ERROR("Error content"),
	DATE_ERROR("Error date");
	
	private String title;
	
	InputDataNewsValidation(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String nameToLower() {
		return this.name().toLowerCase();
	}

}
