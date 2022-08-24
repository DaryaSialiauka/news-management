package by.it_academy.controller.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.it_academy.bean.News;
import by.it_academy.controller.Command;
import by.it_academy.controller.exception.AuthenticationException;
import by.it_academy.service.NewsService;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.exception.AddNewsServiceException;
import by.it_academy.service.exception.DataNewsValidationException;
import by.it_academy.service.exception.ServiceException;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.DateAndCalendar;
import by.it_academy.util.InputDataNewsValidation;
import by.it_academy.util.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddNews implements Command {

	private final static Logger LOG = LogManager.getLogger(DoAddNews.class);

	private final static NewsService provider = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id_news;
		try {
			News news = reqToNews(request, response);

			request.getSession(true).setAttribute(AttributeAndParameter.NEWS, news);
			id_news = provider.addNews(news);
			request.getSession(true).removeAttribute(AttributeAndParameter.NEWS);

			response.sendRedirect(JSPPageName.VIEW_NEWS + AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.NEWS_ID + AttributeAndParameter.EQUALS + id_news);

		} catch (AddNewsServiceException | ServiceException e) {
			LOG.error(e);

			request.getSession(true).setAttribute("url", JSPPageName.ADD_NEWS_PAGE);

			response.sendRedirect(JSPPageName.ADD_NEWS_PAGE + AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.ERROR_ADD_EDIT_NEWS + AttributeAndParameter.EQUALS + e.getMessage());
		} catch (AuthenticationException e) {
			LOG.error(e);

			request.getSession(true).setAttribute("url", JSPPageName.SING_IN_PAGE);

			response.sendRedirect(JSPPageName.SING_IN_PAGE + AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.ERROR_AUTHENTICATION + AttributeAndParameter.EQUALS + e.getMessage());
		} catch (DataNewsValidationException e) {

			LOG.error(e);

			request.getSession(true).setAttribute("url", JSPPageName.ADD_NEWS_PAGE);

			response.sendRedirect(JSPPageName.ADD_NEWS_PAGE + AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.ERROR_ADD_EDIT_NEWS + AttributeAndParameter.EQUALS
					+ AttributeAndParameter.ERROR_ADD_NEWS + errorMapInput(e));
		}
	}

	private static News reqToNews(HttpServletRequest request, HttpServletResponse response)
			throws IOException, AuthenticationException {

		News news = null;

		Calendar date_news;
		int id_author = 0;

		try {
			id_author = (int) request.getSession(true).getAttribute(AttributeAndParameter.USER_ID);
		} catch (NullPointerException e) {
			throw new AuthenticationException("Error authentication", e);
		}

		try {
			date_news = DateAndCalendar.strToCalendar(request.getParameter(AttributeAndParameter.NEWS_DATE));
		} catch (ParseException e) {
			date_news = new GregorianCalendar();
		}

		news = new News(0, request.getParameter(AttributeAndParameter.NEWS_TITLE),
				request.getParameter(AttributeAndParameter.NEWS_BRIEF),
				request.getParameter(AttributeAndParameter.NEWS_CONTENT), date_news, id_author);
		LOG.info(news.toString());
		return news;
	}

	private static String errorMapInput(DataNewsValidationException e) {

		String param = "";
		Iterator<Map.Entry<InputDataNewsValidation, String>> entries = e.getValidMap().entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<InputDataNewsValidation, String> entry = entries.next();
			param += AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.styleError(entry.getKey().name().toLowerCase())
					+ AttributeAndParameter.EQUALS + AttributeAndParameter.ERROR_STYLE;
			param += AttributeAndParameter.SEPARATOR + entry.getKey().name().toLowerCase()
					+ AttributeAndParameter.EQUALS + entry.getValue();
		}

		LOG.info(param);
		return param;

	}

}
