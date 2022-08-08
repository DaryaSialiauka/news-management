package by.it_academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.NotFoundUserDAOException;
import by.it_academy.dao.util.ConnectionPool;
import by.it_academy.dao.util.ConnectionPoolException;

public class UserDAOImpl implements UserDAO {

	private static ConnectionPool provider = ConnectionPool.getInstance();

	@Override
	public Role authentication(String login, String password) throws NotFoundUserDAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		Role role;

		try {
			con = provider.takeConnection();

			sql = "SELECT role FROM user INNER JOIN roles ON user.roles_id = roles.id WHERE login=(?) AND password=(?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				role = Role.getRole(rs.getString(1));
			} else {
				throw new NotFoundUserDAOException("User not found.");
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new NotFoundUserDAOException("User not found.");
		} finally {
			closeConnection(con, ps, rs);
		}

		return role;
	}

	public boolean addUser(User user) throws AddUserDAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		int idUser = 0;
		int rezultRows;

		try {
			con = provider.takeConnection();

			con.setAutoCommit(false);

			sql = "INSERT INTO user(roles_id,login,password) VALUE(?,?,?)";
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, idRole(user.getRole().getTitle()));
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());

			rezultRows = ps.executeUpdate();

			if (rezultRows == 0) {
				throw new AddUserDAOException("Error adding user.");
			}

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idUser = rs.getInt(1);
			} else {
				throw new AddUserDAOException("Error adding user.");
			}

			ps.close();

			sql = "INSERT INTO user_data(user_id,firstname,lastname,datebirth,phone,email) VALUE(?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setInt(1, idUser);
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setDate(4, new java.sql.Date(user.getDatebirth().getTimeInMillis()));
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getEmail());

			ps.executeUpdate();

			con.commit();

		} catch (ConnectionPoolException | SQLException e) {
			rollbackConnection(con);
			throw new AddUserDAOException("Error adding user.", e);
		} finally {
			closeConnection(con, ps, rs);
		}
		return true;
	}

	@Override
	public boolean findLogin(String login) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean findLogin = false;

		String sql;

		try {
			con = provider.takeConnection();
			sql = "SELECT * FROM user WHERE login=(?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, login);
			rs = ps.executeQuery();

			if (rs.next()) {
				findLogin = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Login search error.");
		} finally {
			closeConnection(con, ps, rs);
		}
		return findLogin;
	}

	@Override
	public boolean findEmail(String email) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean findEmail = false;

		String sql;

		try {
			con = provider.takeConnection();
			sql = "SELECT * FROM user_data WHERE email=(?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				findEmail = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Email search error.");
		} finally {
			closeConnection(con, ps, rs);
		}
		return findEmail;
	}

	@Override
	public boolean findPhone(String phone) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean findPhone = false;

		String sql;

		try {
			con = provider.takeConnection();
			sql = "SELECT * FROM user_data WHERE phone=(?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, phone);
			rs = ps.executeQuery();

			if (rs.next()) {
				findPhone = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Phone search error.");
		} finally {
			closeConnection(con, ps, rs);
		}
		return findPhone;
	}

	private int idRole(String str_role) throws AddUserDAOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql;
		int role = 0;

		try {
			con = provider.takeConnection();
			sql = "SELECT id,role FROM roles WHERE role=(?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, str_role);

			rs = ps.executeQuery();

			if (rs.next()) {
				role = rs.getInt(1);
			} else {
				throw new AddUserDAOException("Role not found.");
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new AddUserDAOException("Role not found.");
		} finally {
			closeConnection(con, ps, rs);
		}

		return role;
	}

	private void rollbackConnection(Connection con) throws AddUserDAOException {
		try {
			con.rollback();
		} catch (SQLException e) {
			throw new AddUserDAOException("Error rollback.", e);
		}
	}

	private void closeConnection(Connection con, Statement s, ResultSet rs) {
		if (!Objects.isNull(rs)) {
			provider.closeConnection(con, s, rs);
		} else {
			provider.closeConnection(con, s);
		}
	}

}
