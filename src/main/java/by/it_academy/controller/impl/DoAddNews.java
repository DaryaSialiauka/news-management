package by.it_academy.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
import by.it_academy.util.InputDataNewsValidation;
import by.it_academy.util.JSPPageName;
import by.it_academy.util.NewsError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddNews implements Command {

	private final static Logger LOG = LogManager.getLogger(DoAddNews.class);

	private final static NewsService provider = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews;
		StringBuilder url;

		request.getSession(true).setAttribute(AttributeAndParameter.URL, JSPPageName.ADD_NEWS_PAGE);

		try {

			News news = reqToNews(request);

			if (news == null) {
				LOG.error("News is null.");
				newsError(request, response, "");
			}

			request.getSession(true).setAttribute(AttributeAndParameter.NEWS, news);

			idNews = provider.addNews(news);

			if (idNews == 0) {
				LOG.error("News not added.");
				newsError(request, response, "");
			}

			url = new StringBuilder();
			url.append(JSPPageName.VIEW_NEWS).append(AttributeAndParameter.SEPARATOR)
					.append(AttributeAndParameter.NEWS_ID).append(AttributeAndParameter.EQUALS).append(idNews);

			request.getSession(true).removeAttribute(AttributeAndParameter.NEWS);

			response.sendRedirect(url.toString());

		} catch (AddNewsServiceException | ServiceException e) {
			LOG.error(e);

			newsError(request, response, "");

		} catch (AuthenticationException e) {
			LOG.error(e);

			url = new StringBuilder();
			url.append(JSPPageName.SING_IN_PAGE).append(AttributeAndParameter.SEPARATOR)
					.append(AttributeAndParameter.ERROR_AUTHENTICATION).append(AttributeAndParameter.EQUALS)
					.append(e.getMessage());

			response.sendRedirect(url.toString());
		} catch (DataNewsValidationException e) {

			newsError(request, response, errorMapInput(e).toString());
			LOG.error(e);

		}
	}

	private static void newsError(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException {
		StringBuilder url = new StringBuilder();
		url.append(JSPPageName.ADD_NEWS_PAGE).append(AttributeAndParameter.SEPARATOR)
				.append(AttributeAndParameter.ERROR_ADD_EDIT_NEWS).append(AttributeAndParameter.EQUALS)
				.append(AttributeAndParameter.ERROR_ADD_NEWS).append(message);

		response.sendRedirect(url.toString());
	}

	private static News reqToNews(HttpServletRequest request) throws IOException, AuthenticationException {

		News news = null;

		LocalDate date_news = LocalDate.now();
		int id_author = 0;

		try {
			id_author = (int) request.getSession(true).getAttribute(AttributeAndParameter.USER_ID);
		} catch (NullPointerException e) {
			throw new AuthenticationException("Error authentication", e);
		}

		try {
			date_news = LocalDate.parse(request.getParameter(AttributeAndParameter.NEWS_DATE));
		} catch (DateTimeParseException e) {
			throw new NewsError();
		}

		news = new News(0, request.getParameter(AttributeAndParameter.NEWS_TITLE).trim(),
				request.getParameter(AttributeAndParameter.NEWS_BRIEF).trim(),
				request.getParameter(AttributeAndParameter.NEWS_CONTENT).trim(), date_news, id_author);
		LOG.info(news);
		return news;
	}

	private static StringBuilder errorMapInput(DataNewsValidationException e) {

		StringBuilder param = new StringBuilder();
		Iterator<Map.Entry<InputDataNewsValidation, String>> entries = e.getValidMap().entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<InputDataNewsValidation, String> entry = entries.next();
			param.append(AttributeAndParameter.SEPARATOR)
					.append(AttributeAndParameter.styleError(entry.getKey().name().toLowerCase()))
					.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.ERROR_STYLE);
			param.append(AttributeAndParameter.SEPARATOR).append(entry.getKey().name().toLowerCase())
					.append(AttributeAndParameter.EQUALS).append(entry.getValue());
		}

		LOG.info(param);
		return param;

	}

}
