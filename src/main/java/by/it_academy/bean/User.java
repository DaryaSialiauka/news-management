package by.it_academy.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

import by.it_academy.util.Role;

public class User implements Serializable{

	private static final long serialVersionUID = -2253278249530924124L;
	
	
	private String firstname;
	private String lastname;
	private String login;
	private char[] password;
	private String phone;
	private String email;
	private LocalDate datebirth;
	private Role role;

	public User() {
		this.firstname = "";
		this.lastname = "";
		this.login = "";
		this.password = null;
		this.phone = "";
		this.email = "";
		this.datebirth = LocalDate.now();
		this.role = Role.USER;
	}
	

	public User(String firstname, String lastname, String login, char[] password, String phone, String email,
			LocalDate datebirth, Role role) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.datebirth = datebirth;
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public LocalDate getDatebirth() {
		return datebirth;
	}

	public void setDatebirth(LocalDate datebirth) {
		this.datebirth = datebirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datebirth == null) ? 0 : datebirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (datebirth == null) {
			if (other.datebirth != null)
				return false;
		} else if (!datebirth.equals(other.datebirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (!Arrays.equals(password, other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", password="
				+ Arrays.toString(password) + ", phone=" + phone + ", email=" + email + ", datebirth=" + datebirth
				+ ", role=" + role + "]";
	}

}