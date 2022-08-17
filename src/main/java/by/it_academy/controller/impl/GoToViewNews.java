package by.it_academy.controller.impl;

import java.io.IOException;

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

	private final static NewsService provider = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id;
		News news = null;

		try {
			id = Integer.parseInt(request.getParameter("id_news"));
		} catch (NumberFormatException e) {
			id = 1;
		}

		try {
			news = provider.readNews(id);
			System.out.println(news.toString());
		} catch (FindNewsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("news", news);
		request.getRequestDispatcher(JSPPageName.BASE_PAGE + "?" + AttributeAndParameter.BODY
				+ AttributeAndParameter.EQUALS + AttributeAndParameter.VIEW_NEWS).forward(request, response);

	}

}
