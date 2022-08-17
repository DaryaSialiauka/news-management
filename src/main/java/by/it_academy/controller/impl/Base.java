package by.it_academy.controller.impl;

import java.io.IOException;
import java.util.List;

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
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FindNewsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("news", news);
		request.setAttribute("quantityPage", quantityPage);
		request.getRequestDispatcher(JSPPageName.BASE_PAGE + "?" + AttributeAndParameter.BODY
				+ AttributeAndParameter.EQUALS + AttributeAndParameter.NEWS).forward(request, response);

	}

}
