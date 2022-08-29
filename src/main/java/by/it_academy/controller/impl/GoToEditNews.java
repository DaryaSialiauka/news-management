package by.it_academy.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.it_academy.bean.News;
import by.it_academy.controller.Command;
import by.it_academy.service.NewsService;
import by.it_academy.service.ServiceProvider;
import by.it_academy.service.exception.FindNewsServiceException;
import by.it_academy.service.exception.ServiceException;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToEditNews implements Command {

	private final static Logger LOG = LogManager.getLogger(GoToEditNews.class);
	private final static NewsService provider = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews = 0;
		News news;

		StringBuilder url;

		try {
			idNews = Integer.parseInt(request.getParameter(AttributeAndParameter.NEWS_ID));
		} catch (NumberFormatException e) {
			LOG.error(e);
			newsNotFound(request, response, "News not found");
		}

		url = new StringBuilder();
		url.append(JSPPageName.EDIT_NEWS_PAGE).append(AttributeAndParameter.SEPARATOR)
				.append(AttributeAndParameter.NEWS_ID).append(AttributeAndParameter.EQUALS).append(idNews);
		request.getSession(true).setAttribute(AttributeAndParameter.URL, url.toString());

		try {
			news = provider.readNews(idNews);

			request.getSession(true).removeAttribute(AttributeAndParameter.NEWS);
			request.setAttribute(AttributeAndParameter.NEWS, news);

			url = new StringBuilder();
			url.append(JSPPageName.BASE_PAGE).append("?").append(AttributeAndParameter.BODY)
					.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.ADD_NEWS);

			request.getRequestDispatcher(url.toString()).forward(request, response);

		} catch (FindNewsServiceException | ServiceException e) {
			LOG.error(e);
			newsNotFound(request, response, e.getMessage());
		}

	}

	private static void newsNotFound(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		StringBuilder url = new StringBuilder();
		url.append(JSPPageName.BASE_PAGE).append("?").append(AttributeAndParameter.BODY)
				.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.VIEW_NEWS)
				.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.NEWS_ERROR)
				.append(AttributeAndParameter.EQUALS).append(message);
		request.getRequestDispatcher(url.toString()).forward(request, response);

	}

}
