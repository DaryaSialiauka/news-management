package by.it_academy.controller.impl;

import java.io.IOException;
import java.util.List;

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

public class Base implements Command {

	private final static Logger LOG = LogManager.getLogger(Base.class);
	private static final NewsService provider = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> news = null;
		int quantityPage = 0;

		int numPage;

		try {
			numPage = Integer.parseInt(request.getParameter("pagenum"));
		} catch (NumberFormatException e) {
			numPage = 1;
		}

		try {
			news = provider.readNewsList(5, numPage);
			quantityPage = provider.quantityPage(5);
			request.setAttribute(AttributeAndParameter.NEWS, news);
			request.setAttribute("quantityPage", quantityPage);

			request.getSession(true).setAttribute("url", JSPPageName.LIST_NEWS);

			request.getRequestDispatcher(JSPPageName.BASE_PAGE + "?" + AttributeAndParameter.BODY
					+ AttributeAndParameter.EQUALS + AttributeAndParameter.NEWS).forward(request, response);
		} catch (FindNewsServiceException | ServiceException e) {

			request.getSession(true).setAttribute("url", JSPPageName.LIST_NEWS);

			request.getRequestDispatcher(JSPPageName.BASE_PAGE + "?" + AttributeAndParameter.BODY
					+ AttributeAndParameter.EQUALS + AttributeAndParameter.NEWS + AttributeAndParameter.SEPARATOR
					+ AttributeAndParameter.NEWS_ERROR + AttributeAndParameter.EQUALS + e.getMessage())
					.forward(request, response);
			LOG.error(e);
		}

	}

}