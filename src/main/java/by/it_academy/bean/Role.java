package by.it_academy.bean;

public enum Role {
	ADMIN("admin"), 
	USER("user"),
	REPORTER("reporter");

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
		throw new IllegalArgumentException("No enum found with str: [" + str + "]");
	}

}
