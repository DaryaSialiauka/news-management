package by.it_academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.it_academy.dao.ValidationUserDAO;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.util.ConnectionPool;
import by.it_academy.dao.util.ConnectionPoolException;

public class ValidationUserDAOImpl implements ValidationUserDAO {
	
	private final static ConnectionPool provider = ConnectionPool.getInstance();

	@Override
	public boolean findLogin(String login) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String findLogin;

		try {
			con = provider.takeConnection();
			findLogin = "SELECT * FROM user WHERE login=(?)";
			ps = con.prepareStatement(findLogin);

			ps.setString(1, login);
			rs = ps.executeQuery();

			return rs.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}
	}

	@Override
	public boolean findEmail(String email) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String findEmail;

		try {
			con = provider.takeConnection();
			findEmail = "SELECT * FROM user_data WHERE email=(?)";
			ps = con.prepareStatement(findEmail);

			ps.setString(1, email);
			rs = ps.executeQuery();

			return rs.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

	}

	@Override
	public boolean findPhone(String phone) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String findPhone;

		try {
			con = provider.takeConnection();
			findPhone = "SELECT * FROM user_data WHERE phone=(?)";
			ps = con.prepareStatement(findPhone);

			ps.setString(1, phone);
			rs = ps.executeQuery();

			return rs.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}
	}

}
