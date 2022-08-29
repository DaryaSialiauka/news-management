package by.it_academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.it_academy.bean.News;
import by.it_academy.dao.NewsDAO;
import by.it_academy.dao.exception.AddNewsDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindNewsDAOException;
import by.it_academy.dao.util.ConnectionPool;
import by.it_academy.dao.util.ConnectionPoolException;
import by.it_academy.util.DateUtil;
import by.it_academy.util.TableColumn;

public class NewsDAOImpl implements NewsDAO {

	private final static Logger LOG = LogManager.getLogger(NewsDAOImpl.class);
	private final static ConnectionPool provider = ConnectionPool.getInstance();

	@Override
	public int addNews(News news) throws AddNewsDAOException, DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int resultRows = 0;

		String insertNews;

		try {
			con = provider.takeConnection();

			insertNews = "INSERT INTO news(title, brief, content, date, author_id) VALUE(?,?,?,?,?)";
			
			ps = con.prepareStatement(insertNews, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setDate(4, DateUtil.localdateToSQLDate(news.getDate_create()));
			ps.setInt(5, news.getId_author());

			resultRows = ps.executeUpdate();

			if (resultRows == 0) {
				throw new AddNewsDAOException("Error adding news.");
			}

			rs = ps.getGeneratedKeys();
			if (!rs.next()) {
				throw new AddNewsDAOException("Error adding news.");
			}

			return rs.getInt(Statement.RETURN_GENERATED_KEYS);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

	}

	@Override
	public News readNews(int id) throws FindNewsDAOException, DAOException {
		News news = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getNews = null;

		try {
			con = provider.takeConnection();
			getNews = "SELECT id, title, brief, content, author_id, date FROM news WHERE id = ?";
			ps = con.prepareStatement(getNews);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new FindNewsDAOException("News not found.");
			}

			news = new News(rs.getInt(TableColumn.NEWS_ID), rs.getString(TableColumn.NEWS_TITLE),
					rs.getString(TableColumn.NEWS_BRIEF), rs.getString(TableColumn.NEWS_CONTENT),
					rs.getDate(TableColumn.NEWS_DATE).toLocalDate(),
					rs.getInt(TableColumn.NEWS_AUTHOR));

			return news;
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

	}

	@Override
	public List<News> readNewsList(int quantityNewsPage, int page) throws FindNewsDAOException, DAOException {
		List<News> news = new ArrayList<News>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String getListNews = null;

		try {
			con = provider.takeConnection();
			getListNews = "SELECT id, title, brief, author_id, date FROM news ORDER BY date DESC, id DESC LIMIT ? OFFSET ?";

			ps = con.prepareStatement(getListNews);
			

			ps.setInt(1, quantityNewsPage);
			ps.setInt(2, (page - 1) * quantityNewsPage);
			

			rs = ps.executeQuery();

			while (rs.next()) {
				news.add(new News(rs.getInt(TableColumn.NEWS_ID), rs.getString(TableColumn.NEWS_TITLE),
						rs.getString(TableColumn.NEWS_BRIEF), "",
						rs.getDate(TableColumn.NEWS_DATE).toLocalDate(),
						rs.getInt(TableColumn.NEWS_AUTHOR)));
			}

			if (news.isEmpty()) {
				throw new FindNewsDAOException("News not found.");
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

		return news;
	}

	@Override
	public boolean deleteNews(int id) {


		return false;
	}

	@Override
	public boolean updateNews(News news) throws AddNewsDAOException, DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int resultRows = 0;

		String updateNews;
		try {
			con = provider.takeConnection();
			
			updateNews = "UPDATE news SET title = ? , brief = ?, content = ?, date = ? WHERE id = ?";
			ps = con.prepareStatement(updateNews, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setDate(4, DateUtil.localdateToSQLDate(news.getDate_create()));
			ps.setInt(5, news.getId());

			resultRows = ps.executeUpdate();

			if (resultRows == 0) {
				throw new AddNewsDAOException("Error adding news.");
			}

			return true;
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}
	}

	@Override
	public int quantityNews() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String getQuantity;

		try {
			con = provider.takeConnection();

			getQuantity = "SELECT COUNT(*) FROM news";

			ps = con.prepareStatement(getQuantity);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return 0;
			}

			return rs.getInt(1);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Internal error. Please try later.", e);
		} finally {
			provider.closeStatementAndResult(ps, rs);
			provider.closeConnection(con);
		}

	}

}
