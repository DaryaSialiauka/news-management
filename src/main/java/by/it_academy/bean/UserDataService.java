package by.it_academy.bean;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserDataService {

	private String firstname;
	private String lastname;
	private String login;
	private String phone;
	private String email;
	private Calendar datebirth;

	public UserDataService() {
		this.firstname = "";
		this.lastname = "";
		this.login = "";
		this.phone = "";
		this.email = "";
		this.datebirth = new GregorianCalendar();
	}

	public UserDataService(String firstname, String lastname, String login, String phone, String email,
			Calendar datebirth) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.phone = phone;
		this.email = email;
		this.datebirth = datebirth;
	}

	public UserDataService(User user) {
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.login = user.getLogin();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.datebirth = user.getDatebirth();
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public Calendar getDatebirth() {
		return datebirth;
	}

	public void setDatebirth(Calendar datebirth) {
		this.datebirth = datebirth;
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
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		UserDataService other = (UserDataService) obj;
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserService [firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", phone="
				+ phone + ", email=" + email + ", datebirth=" + datebirth + "]";
	}

}
