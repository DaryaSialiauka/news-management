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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoEditNews implements Command {

	private final static Logger LOG = LogManager.getLogger(DoEditNews.class);

	private final static NewsService provider = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		StringBuilder url;
		try {
			url = new StringBuilder();
			
			url.append(JSPPageName.EDIT_NEWS_PAGE).append(AttributeAndParameter.SEPARATOR)
					.append(AttributeAndParameter.NEWS_ID).append(AttributeAndParameter.EQUALS)
					.append(request.getParameter(AttributeAndParameter.NEWS_ID));
			request.getSession(true).setAttribute(AttributeAndParameter.URL, url.toString());

			news = reqToNews(request);

			if (news == null) {
				LOG.error("News not added");
				newsError(request, response, "");
			}

			request.setAttribute(AttributeAndParameter.NEWS, news);

			if (!provider.updateNews(news)) {
				LOG.error("News not added.");
				newsError(request, response, "");
			}
			request.removeAttribute(AttributeAndParameter.NEWS);

			url = new StringBuilder();
			url.append(JSPPageName.VIEW_NEWS).append(AttributeAndParameter.SEPARATOR)
					.append(AttributeAndParameter.NEWS_ID).append(AttributeAndParameter.EQUALS).append(news.getId());

			response.sendRedirect(url.toString());
		} catch (AddNewsServiceException | ServiceException e) {

			LOG.error(e);
			newsError(request, response, "");

		} catch (AuthenticationException e) {
			
			LOG.error(e);
			authenticationError(request, response, e.getMessage());

		} catch (DataNewsValidationException e) {

			LOG.error(e);
			newsError(request, response, errorMapInput(e).toString());
		}

	}

	private static void newsError(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException {
		StringBuilder url = new StringBuilder();
		url.append(JSPPageName.EDIT_NEWS_PAGE).append(AttributeAndParameter.SEPARATOR)
				.append(AttributeAndParameter.ERROR_ADD_EDIT_NEWS).append(AttributeAndParameter.EQUALS)
				.append(AttributeAndParameter.ERROR_ADD_NEWS).append(message);

		response.sendRedirect(url.toString());
	}

	private static void authenticationError(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException {
		StringBuilder url = new StringBuilder();
		url.append(JSPPageName.SING_IN_PAGE).append(AttributeAndParameter.SEPARATOR)
				.append(AttributeAndParameter.ERROR_AUTHENTICATION).append(AttributeAndParameter.EQUALS)
				.append(message);

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
			//TODO
		}

		news = new News(Integer.parseInt(request.getParameter(AttributeAndParameter.NEWS_ID)),
				request.getParameter(AttributeAndParameter.NEWS_TITLE).trim(),
				request.getParameter(AttributeAndParameter.NEWS_BRIEF).trim(),
				request.getParameter(AttributeAndParameter.NEWS_CONTENT).trim(), date_news, id_author);

		LOG.info(news);

		return news;
	}

	private static StringBuilder errorMapInput(DataNewsValidationException e) {

		StringBuilder url = new StringBuilder();
		Iterator<Map.Entry<InputDataNewsValidation, String>> entries = e.getValidMap().entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<InputDataNewsValidation, String> entry = entries.next();
			url.append(AttributeAndParameter.SEPARATOR)
					.append(AttributeAndParameter.styleError(entry.getKey().name().toLowerCase()))
					.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.ERROR_STYLE)
					.append(AttributeAndParameter.SEPARATOR + entry.getKey().name().toLowerCase())
					.append(AttributeAndParameter.EQUALS).append(entry.getValue());
		}

		LOG.info(url);
		return url;

	}

}
