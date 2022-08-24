package by.it_academy.bean;

import java.util.Calendar;

import by.it_academy.util.Role;

public class UserBuilder {

	private String firstname;
	private String lastname;
	private String login;
	private char[] password;
	private String phone;
	private String email;
	private Calendar datebirth;
	private Role role;

	public UserBuilder() {

	}

	public UserBuilder firstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public UserBuilder lastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public UserBuilder login(String login) {
		this.login = login;
		return this;
	}

	public UserBuilder password(char[] password) {
		this.password = password;
		return this;
	}

	public UserBuilder phone(String phone) {
		this.phone = phone;
		return this;
	}

	public UserBuilder email(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder datebirth(Calendar datebirth) {
		this.datebirth = datebirth;
		return this;
	}

	public UserBuilder role(Role role) {
		this.role = role;
		return this;
	}

	public User build() {
		
			
		User user = new User(this.firstname, this.lastname, this.login, this.password, this.phone, this.email,
				this.datebirth, this.role);
		return user;
	}
}
