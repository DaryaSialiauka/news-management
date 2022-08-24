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

		request.getSession(true).removeAttribute(AttributeAndParameter.NEWS);

		try {
			id = Integer.parseInt(request.getParameter(AttributeAndParameter.NEWS_ID));
		} catch (NumberFormatException e) {
			id = 1;
		}

		try {
			news = provider.readNews(id);
			request.setAttribute(AttributeAndParameter.NEWS, news);

			request.getSession(true).setAttribute("url", JSPPageName.VIEW_NEWS + AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.NEWS_ID + AttributeAndParameter.EQUALS + id);
			request.getRequestDispatcher(JSPPageName.BASE_PAGE + "?" + AttributeAndParameter.BODY
					+ AttributeAndParameter.EQUALS + AttributeAndParameter.VIEW_NEWS).forward(request, response);

		} catch (FindNewsServiceException | ServiceException e) {
			request.getSession(true).setAttribute("url", JSPPageName.VIEW_NEWS + AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.NEWS_ID + AttributeAndParameter.EQUALS + id);

			request.getRequestDispatcher(JSPPageName.BASE_PAGE + "?" + AttributeAndParameter.BODY
					+ AttributeAndParameter.EQUALS + AttributeAndParameter.VIEW_NEWS + AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.NEWS_ERROR + AttributeAndParameter.EQUALS + e.getMessage())
					.forward(request, response);
			LOG.error(e);

		}

	}

}