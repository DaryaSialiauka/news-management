package by.it_academy.dao;

import java.util.List;

import by.it_academy.bean.News;
import by.it_academy.dao.exception.AddNewsDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindNewsDAOException;

public interface NewsDAO {

	int addNews(News news) throws AddNewsDAOException, DAOException;
	
	News readNews(int id) throws FindNewsDAOException, DAOException;
	
	boolean updateNews(News news) throws AddNewsDAOException, DAOException;
	
	boolean deleteNews(int id);

	List<News> readNewsList(int quantityNewsPage, int page) throws FindNewsDAOException, DAOException;
	
	int quantityNews() throws DAOException;
	
	
}
