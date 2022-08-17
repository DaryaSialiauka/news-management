package by.it_academy.util;

public enum Role {
	ADMIN("admin"), 
	USER("user"),
	REPORTER("reporter"),
	UNKNOWN("unknown");

	private final String title;

	Role(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public static Role getRole(String str) {

		for (Role role : values()) {
			if (role.getTitle().equalsIgnoreCase(str)) {
				return role;
			}
		}
		return UNKNOWN;
	}

}
