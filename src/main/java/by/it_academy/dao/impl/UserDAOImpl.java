package by.it_academy.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.NotFoundUserDAOException;

public class UserDAOImpl implements UserDAO {

	@Override
	public Role authentication(String login, String password) throws NotFoundUserDAOException {

		// throw new NotFoundUserDAOException("Not found user");
		return Role.ADMIN;
	}

	public boolean addUser(User user) throws AddUserDAOException {
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news?useSSL=false&serverTimezone=UTC", "root",
					"7786405");

			String sql = "INSERT INTO news.user(roles_id,login,password) VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, 2);
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());

			ps.executeUpdate();
		} catch (SQLException |ClassNotFoundException e1) { // TODO Auto-generated catch block
			throw new AddUserDAOException("Not added user");
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public boolean findLogin(String login) {
		return false;
	}

	@Override
	public boolean findEmail(String email) {
		return false;
	}

	@Override
	public boolean findPhone(String phone) {
		return false;
	}

}
