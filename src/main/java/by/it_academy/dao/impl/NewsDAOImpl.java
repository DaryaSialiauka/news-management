package by.it_academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import by.it_academy.bean.News;
import by.it_academy.dao.NewsDAO;
import by.it_academy.dao.exception.AddNewsDAOException;
import by.it_academy.dao.exception.FindNewsDAOException;
import by.it_academy.dao.util.ConnectionPool;
import by.it_academy.dao.util.ConnectionPoolException;
import by.it_academy.util.DateAndCalendar;

public class NewsDAOImpl implements NewsDAO {

	private final static ConnectionPool provider = ConnectionPool.getInstance();
	private final static int STATUS_CREATE = 1;
	private final static int STATUS_UPDATE = 2;

	@Override
	public int addNews(News news) throws AddNewsDAOException {
		Connection con = null;
		int idNews = 0;

		try {
			con = provider.takeConnection();
			con.setAutoCommit(false);

			idNews = insertNews(con, news);

			insertHistoryChangesNews(con, news, idNews, STATUS_CREATE);

			con.commit();

		} catch (ConnectionPoolException | SQLException e) {
			rollbackConnection(con);
		} finally {
			provider.closeConnection(con);
		}

		return idNews;
	}

	@Override
	public News readNews(int id) throws FindNewsDAOException {

		
		  News news = null;
		  
			/*
			 * try { news = getNews(id);
			 * 
			 * news.setDate_create(getDateChange(id));
			 * 
			 * } catch (ConnectionPoolException | SQLException e) { throw new
			 * FindNewsDAOException("News not found.", e); }
			 */
		  
		  return news;
		 
	}

	/*
	 * private News getNews(int id) throws ConnectionPoolException,
	 * FindNewsDAOException, SQLException { Connection con = null; PreparedStatement
	 * ps = null; ResultSet rs = null;
	 * 
	 * String getNews = "";
	 * 
	 * News news = null;
	 * 
	 * //TO DO getNews =
	 * "SELECT news.id as id, news.title as title, news.brief as brief, news.content as content, history_changes_news.editor_id as editor_id FROM news INNER JOIN history_changes_news ON news.id = history_changes_news.news_id WHERE history_changes_news.status_id=? AND news.id=? AND "
	 * ;
	 * 
	 * try { con = provider.takeConnection(); ps = con.prepareStatement(getNews);
	 * ps.setInt(1, STATUS_CREATE); ps.setInt(2, id); rs = ps.executeQuery();
	 * 
	 * if (!rs.next()) { throw new FindNewsDAOException("News not found."); }
	 * 
	 * news = new News(rs.getInt("id"), rs.getString("title"),
	 * rs.getString("brief"), rs.getString("content"), null,
	 * rs.getInt("editor_id"));
	 * 
	 * return news; } finally { provider.closeStatementAndResult(ps, rs);
	 * provider.closeConnection(con); } }
	 * 
	 * private Calendar getDateChange(int id) throws ConnectionPoolException,
	 * FindNewsDAOException, SQLException { Connection con = null; PreparedStatement
	 * ps = null; ResultSet rs = null;
	 * 
	 * String getDate = "";
	 * 
	 * getDate =
	 * "SELECT  MAX(history_changes_news.date) as maxdate FROM history_changes_news WHERE history_changes_news.news_id=?"
	 * ; try { con = provider.takeConnection(); ps = con.prepareStatement(getDate);
	 * ps.setInt(1, id); rs = ps.executeQuery();
	 * 
	 * if (!rs.next()) { throw new FindNewsDAOException("News not found."); }
	 * 
	 * return DateAndCalendar.dateToCalendar(rs.getDate("maxdate")); } finally {
	 * provider.closeStatementAndResult(ps, rs); provider.closeConnection(con); }
	 * 
	 * }
	 */

	@Override
	public int updateNews(News news) throws AddNewsDAOException {
		Connection con = null;

		try {
			con = provider.takeConnection();
			con.setAutoCommit(false);

			updateNews(con, news);

			insertHistoryChangesNews(con, news, news.getId(), STATUS_UPDATE);

			con.commit();

		} catch (ConnectionPoolException | SQLException e) {
			rollbackConnection(con);
		} finally {
			provider.closeConnection(con);
		}

		return news.getId();
	}

	@Override
	public boolean deleteNews(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	private void rollbackConnection(Connection con) throws AddNewsDAOException {
		try {
			con.rollback();
		} catch (SQLException e) {
			throw new AddNewsDAOException("Error rollback.", e);
		}
	}

	private int insertNews(Connection con, News news) throws SQLException, AddNewsDAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int resultRows = 0;

		String insertNews;
		try {
			insertNews = "INSERT INTO news(title, brief, content) VALUE(?,?,?)";
			ps = con.prepareStatement(insertNews, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());

			resultRows = ps.executeUpdate();

			if (resultRows == 0) {
				throw new AddNewsDAOException("Error adding news.");
			}

			rs = ps.getGeneratedKeys();
			if (!rs.next()) {
				throw new AddNewsDAOException("Error adding news.");
			}

			return rs.getInt(Statement.RETURN_GENERATED_KEYS);
		} finally {
			provider.closeStatementAndResult(ps, rs);
		}
	}

	private boolean updateNews(Connection con, News news) throws SQLException, AddNewsDAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int resultRows = 0;

		String updateNews;
		try {
			updateNews = "UPDATE news SET title = ? , brief = ?, content = ? WHERE id = ?";
			ps = con.prepareStatement(updateNews, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setInt(4, news.getId());

			resultRows = ps.executeUpdate();

			if (resultRows == 0) {
				throw new AddNewsDAOException("Error adding news.");
			}

			return true;
		} finally {
			provider.closeStatementAndResult(ps, rs);
		}
	}

	private boolean insertHistoryChangesNews(Connection con, News news, int idNews, int status)
			throws SQLException, AddNewsDAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int resultRows = 0;

		String insertChangesNews;
		try {
			insertChangesNews = "INSERT INTO history_changes_news(editor_id,news_id,date,status_id) VALUE(?,?,?,?)";
			ps = con.prepareStatement(insertChangesNews, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, news.getId_author());
			ps.setInt(2, idNews);
			ps.setDate(3, DateAndCalendar.calendarToDate(news.getDate_create()));
			ps.setInt(4, status);

			resultRows = ps.executeUpdate();

			if (resultRows == 0) {
				throw new AddNewsDAOException("Error adding news.");
			}

			return true;
		} finally {
			provider.closeStatementAndResult(ps, rs);
		}
	}
}
