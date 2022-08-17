package by.it_academy.service;

import java.util.List;

import by.it_academy.bean.News;
import by.it_academy.dao.exception.AddNewsDAOException;
import by.it_academy.service.exception.FindNewsServiceException;
import by.it_academy.service.exception.ServiceException;

public interface NewsService {
	
	int addNews(News news) throws AddNewsDAOException, ServiceException;
	
	News readNews(int id) throws FindNewsServiceException, ServiceException;
	
	List<News> readNewsList(int quantityNewsPage, int page) throws ServiceException, FindNewsServiceException;
	
	boolean updateNews(News news);
	
	boolean deleteNews(int id);
	
	int quantityNews() throws ServiceException;
	
	int quantityPage(int quantityNewsPage) throws ServiceException;

}
