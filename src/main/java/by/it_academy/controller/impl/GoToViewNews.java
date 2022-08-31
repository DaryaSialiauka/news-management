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

public class GoToViewNews implements Command {

	private final static Logger LOG = LogManager.getLogger(GoToViewNews.class);
	private final static NewsService provider = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id;
		News news = null;

		StringBuilder url = new StringBuilder();

		try {
			id = Integer.parseInt(request.getParameter(AttributeAndParameter.NEWS_ID));
		} catch (NumberFormatException e) {
			LOG.error("News ID not found.", e);
			// TODO
			id = 1;
		}

		url.append(JSPPageName.VIEW_NEWS).append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.NEWS_ID)
				.append(AttributeAndParameter.EQUALS).append(id);

		request.getSession(true).setAttribute(AttributeAndParameter.URL, url.toString());

		try {
			news = provider.readNews(id);
			if(news == null) {
				newsNotFound(request, response, "News not found.");
			}
			LOG.info(news);
			
			request.setAttribute(AttributeAndParameter.NEWS, news);

			url = new StringBuilder();
			url.append(JSPPageName.BASE_PAGE).append("?").append(AttributeAndParameter.BODY)
					.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.VIEW_NEWS);

			request.getRequestDispatcher(url.toString()).forward(request, response);

		} catch (FindNewsServiceException | ServiceException e) {

			newsNotFound(request, response, e.getMessage());
			LOG.error(e);
			
		}

	}
	
	private static void newsNotFound(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		StringBuilder url = new StringBuilder();
		url = new StringBuilder();
		url.append(JSPPageName.BASE_PAGE).append("?").append(AttributeAndParameter.BODY)
				.append(AttributeAndParameter.EQUALS).append(AttributeAndParameter.VIEW_NEWS)
				.append(AttributeAndParameter.SEPARATOR).append(AttributeAndParameter.NEWS_ERROR)
				.append(AttributeAndParameter.EQUALS).append(message);
		request.getRequestDispatcher(url.toString()).forward(request, response);
		
	}

}