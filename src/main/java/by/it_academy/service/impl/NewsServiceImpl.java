package by.it_academy.service.impl;

import java.util.List;

import by.it_academy.bean.News;
import by.it_academy.dao.DAOProvider;
import by.it_academy.dao.NewsDAO;
import by.it_academy.dao.exception.AddNewsDAOException;
import by.it_academy.dao.exception.DAOException;
import by.it_academy.dao.exception.FindNewsDAOException;
import by.it_academy.service.NewsService;
import by.it_academy.service.exception.AddNewsServiceException;
import by.it_academy.service.exception.DataNewsValidationException;
import by.it_academy.service.exception.FindNewsServiceException;
import by.it_academy.service.exception.ServiceException;
import by.it_academy.service.validation.ValidationNewsData;

public class NewsServiceImpl implements NewsService {

	private final static NewsDAO provider = DAOProvider.getInstance().getNewsDAO();
	//private final static NewsDataValidation validProvider = ValidationProvider.getInstance().getNewsDataValidation();

	@Override
	public int addNews(News news) throws ServiceException, AddNewsServiceException, DataNewsValidationException {
		int id;
		try {	
			
			ValidationNewsData validNews; 
			ValidationNewsData.ValidBuilder valid = new ValidationNewsData.ValidBuilder();
			
			validNews = valid.titleValid(news.getTitle()).briefValid(news.getBrief()).contentValid(news.getContent()).authorValid(news.getId_author()).dateValid(news.getDate_create()).build();
			
			if(!validNews.getValidData().isEmpty()) {
				throw new DataNewsValidationException(validNews.getValidData(), "News not valid");
			}
			
			id = provider.addNews(news);
			return id;
			
		} catch (AddNewsDAOException e) {
			throw new AddNewsServiceException(e);
		} catch (DAOException e) {
			throw new ServiceException(e);}
	}

	@Override
	public News readNews(int id) throws FindNewsServiceException, ServiceException {
				
		try {
			return provider.readNews(id);
		} catch (FindNewsDAOException e) {
			throw new FindNewsServiceException(e);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<News> readNewsList(int quantityNewsPage, int page) throws ServiceException, FindNewsServiceException {

		List<News> newsList = null;

		try {
			
			newsList = provider.readNewsList(quantityNewsPage, page);
			return newsList;

		} catch (FindNewsDAOException e) {
			throw new FindNewsServiceException(e);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateNews(News news) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNews(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int quantityNews() throws ServiceException {
		try {
			return provider.quantityNews();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int quantityPage(int quantityNewsPage) throws ServiceException {
		try {
			int quantityNews = provider.quantityNews();

			if (quantityNews == 0) {
				return 0;
			}

			return (int) Math.ceil(quantityNews / (double) quantityNewsPage);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
