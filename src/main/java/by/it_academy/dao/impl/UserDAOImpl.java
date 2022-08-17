package by.it_academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.it_academy.bean.User;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindUserDAOException;
import by.it_academy.dao.util.ConnectionPool;
import by.it_academy.dao.util.ConnectionPoolException;
import by.it_academy.util.DateAndCalendar;
import by.it_academy.util.Role;
import by.it_academy.util.TableColumn;

public class UserDAOImpl implements UserDAO {

	private final static ConnectionPool provider = ConnectionPool.getInstance();

	private final static String ID_USER = "id";

	@Override
	public int authentication(String login, char[] password) throws DAOException, FindUserDAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getId;

		try {
			con = provider.takeConnection();

			getId = "SELECT id FROM user WHERE login=(?) AND password=(?)";

			ps = con.prepareStatement(getId);
			ps.setString(1, login);
			ps.setString(2, String.valueOf(password));

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new FindUserDAOException("User not found. Enter another login and password. Or register");
			}

			return rs.getInt(ID_USER);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

	}

	public int addUser(User user) throws DAOException, AddUserDAOException {
		Connection con = null;
		int idUser = 0;

		try {
			con = provider.takeConnection();

			con.setAutoCommit(false);

			idUser = insertUser(con, user);
			insertUserData(con, user, idUser);

			con.commit();

			return idUser;

		} catch (ConnectionPoolException | SQLException e) {
			rollbackConnection(con);
			throw new DAOException("Internal error. Please try later.", e);
		} catch (AddUserDAOException e) {
			rollbackConnection(con);
			throw new AddUserDAOException(e.getMessage(), e);
		} finally {
			provider.closeConnection(con);
		}

	}

	@Override
	public User getUser(int id) throws FindUserDAOException, DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String getUser;

		try {
			con = provider.takeConnection();

			getUser = "SELECT firstname,lastname,phone,email,datebirth,role FROM user INNER JOIN user_data ON user.id = user_data.user_id INNER JOIN roles ON roles.id = user.roles_id WHERE user.id = ?";

			ps = con.prepareStatement(getUser);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new FindUserDAOException("User not found.");
			}
			return new User(rs.getString(TableColumn.USER_DATA_FIRSTNAME), rs.getString(TableColumn.USER_DATA_LASTNAME),
					"", null, rs.getString(TableColumn.USER_DATA_PHONE), rs.getString(TableColumn.USER_DATA_EMAIL),
					DateAndCalendar.dateToCalendar(rs.getDate(TableColumn.USER_DATA_DATEBIRTH)),
					Role.getRole(rs.getString(TableColumn.ROLE_ROLE)));

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

	}

	private final static String ID_COLUMN = "id";

	private int getIdRole(String role_name) throws SQLException, ConnectionPoolException, DAOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String getIdRole;

		try {
			con = provider.takeConnection();
			getIdRole = "SELECT id FROM roles WHERE role=(?)";

			ps = con.prepareStatement(getIdRole);
			ps.setString(1, role_name);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("Role not found.");
			}
			return rs.getInt(ID_COLUMN);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}
	}

	private void rollbackConnection(Connection con) throws DAOException {
		try {
			con.rollback();
		} catch (SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		}
	}

	private int insertUser(Connection con, User user)
			throws SQLException, ConnectionPoolException, AddUserDAOException, DAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String insert_user;
		int resultRows;
		int idRole;

		insert_user = "INSERT INTO user(roles_id, login, password) VALUE(?,?,?)";
		try {
			ps = con.prepareStatement(insert_user, Statement.RETURN_GENERATED_KEYS);

			idRole = getIdRole(user.getRole().getTitle());

			ps.setInt(1, idRole);
			ps.setString(2, user.getLogin());
			ps.setString(3, String.valueOf(user.getPassword()));

			resultRows = ps.executeUpdate();

			if (resultRows == 0) {
				throw new AddUserDAOException("User not found.");
			}

			rs = ps.getGeneratedKeys();

			if (!rs.next()) {
				throw new AddUserDAOException("User not found.");
			}

			return rs.getInt(Statement.RETURN_GENERATED_KEYS);

		} finally {
			provider.closeStatementAndResult(ps, rs);
		}

	}

	private boolean insertUserData(Connection con, User user, int id) throws SQLException, AddUserDAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String insert_user;
		int resultRows;

		insert_user = "INSERT INTO user_data(user_id,firstname,lastname,datebirth,phone,email) VALUE(?,?,?,?,?,?)";

		try {
			ps = con.prepareStatement(insert_user);
			ps.setInt(1, id);
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setDate(4, DateAndCalendar.calendarToDate(user.getDatebirth()));
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getEmail());

			resultRows = ps.executeUpdate();

			if (resultRows == 0) {
				throw new AddUserDAOException("User not found");
			}

			return true;
		} finally {
			provider.closeStatementAndResult(ps, rs);
		}

	}

	private final static String ROLE = "role";

	@Override
	public Role getRole(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String getRole = "SELECT role FROM user INNER JOIN roles ON user.roles_id = roles.id WHERE user.id = ?";

		try {
			con = provider.takeConnection();
			ps = con.prepareStatement(getRole);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return Role.UNKNOWN;
			}

			return Role.getRole(rs.getString(ROLE));

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

	}

}
