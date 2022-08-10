package by.it_academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.AddUserDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindUserDAOException;
import by.it_academy.dao.util.ConnectionPool;
import by.it_academy.dao.util.ConnectionPoolException;
import by.it_academy.util.DateAndCalendar;

public class UserDAOImpl implements UserDAO {

	private final static ConnectionPool provider = ConnectionPool.getInstance();

	private final static String ID_USER = "id";

	@Override
	public int authentication(String login, String password) throws FindUserDAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getId;

		int idUser;

		try {
			con = provider.takeConnection();

			getId = "SELECT id FROM user WHERE login=(?) AND password=(?)";

			ps = con.prepareStatement(getId);
			ps.setString(1, login);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				idUser = rs.getInt(ID_USER);
			} else {
				throw new FindUserDAOException("User not found.");
			}
			
			return idUser;

		} catch (ConnectionPoolException | SQLException e) {
			throw new FindUserDAOException("User not found.");
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

		
	}

	public int addUser(User user) throws AddUserDAOException {
		Connection con = null;
		int idUser = 0;

		try {
			con = provider.takeConnection();

			con.setAutoCommit(false);

			idUser = insertUser(con, user);
			
			System.out.println(idUser);
			
			insertUserData(con, user, idUser);

			con.commit();
			
			return idUser;

		} catch (ConnectionPoolException | SQLException e) {
			rollbackConnection(con);
			throw new AddUserDAOException("Error adding user.", e);
		} finally {
			provider.closeConnection(con);
		}
		
	}

	@Override
	public boolean findLogin(String login) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql;

		try {
			con = provider.takeConnection();
			sql = "SELECT * FROM user WHERE login=(?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, login);
			rs = ps.executeQuery();

			return rs.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Login search error.");
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

		String sql;

		try {
			con = provider.takeConnection();
			sql = "SELECT * FROM user_data WHERE email=(?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, email);
			rs = ps.executeQuery();

			return rs.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Email search error.");
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

		String sql;

		try {
			con = provider.takeConnection();
			sql = "SELECT * FROM user_data WHERE phone=(?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, phone);
			rs = ps.executeQuery();

			return rs.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Phone search error.");
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}
	}

	private final static String ID_COLUMN = "id";
	
	private int getIdRole(String role_name) throws AddUserDAOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql;

		try {
			con = provider.takeConnection();
			sql = "SELECT id FROM roles WHERE role=(?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, role_name);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new AddUserDAOException("Role not found.");
			}
			return rs.getInt(ID_COLUMN);

		} catch (ConnectionPoolException | SQLException e) {
			throw new AddUserDAOException("Role not found.");
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}
	}

	private void rollbackConnection(Connection con) throws AddUserDAOException {
		try {
			con.rollback();
		} catch (SQLException e) {
			throw new AddUserDAOException("Error rollback.", e);
		}
	}

	private int insertUser(Connection con, User user) throws SQLException, AddUserDAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String insert_user;
		int resultRows;

		insert_user = "INSERT INTO user(roles_id, login, password) VALUE(?,?,?)";
		try {
			ps = con.prepareStatement(insert_user, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, getIdRole(user.getRole().getTitle()));
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());

			resultRows = ps.executeUpdate();

			if (resultRows == 0) {
				throw new AddUserDAOException("Error adding user.");
			}
			
			rs = ps.getGeneratedKeys();
			if (!rs.next()) {
				throw new AddUserDAOException("Error adding user.");
			}
			return rs.getInt(Statement.RETURN_GENERATED_KEYS);
			
		} finally {
			provider.closeStatementAndResult(ps, rs);
		}

		
	}

	private void insertUserData(Connection con, User user, int id) throws AddUserDAOException, SQLException {

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
				throw new AddUserDAOException("Error adding user.");
			}
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
			
			if(!rs.next()) {
				throw new DAOException("Role not found.");
			}
						
			return Role.getRole(rs.getString(ROLE));
		}
		catch(ConnectionPoolException | SQLException e) {
			throw new DAOException("Role not found.");
		}
		finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}
		
		
	}

}
