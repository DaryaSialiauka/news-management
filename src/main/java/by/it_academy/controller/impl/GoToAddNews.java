package by.it_academy.controller.impl;

import java.io.IOException;

import by.it_academy.controller.Command;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute("url", JSPPageName.ADD_NEWS_PAGE + AttributeAndParameter.SEPARATOR
				+ AttributeAndParameter.BODY + AttributeAndParameter.EQUALS + AttributeAndParameter.ADD_NEWS);
		request.getRequestDispatcher(JSPPageName.BASE_PAGE + "?" + AttributeAndParameter.BODY
				+ AttributeAndParameter.EQUALS + AttributeAndParameter.ADD_NEWS).forward(request, response);

	}

}
