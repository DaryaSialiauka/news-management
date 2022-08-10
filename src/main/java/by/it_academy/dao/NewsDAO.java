package by.it_academy.dao;

import by.it_academy.bean.News;
import by.it_academy.dao.exception.AddNewsDAOException;
import by.it_academy.dao.exception.FindNewsDAOException;

public interface NewsDAO {

	public int addNews(News news) throws AddNewsDAOException;
	
	public News readNews(int id) throws FindNewsDAOException;
	
	public int updateNews(News news) throws AddNewsDAOException;
	
	public boolean deleteNews(int id);
	
	
}
