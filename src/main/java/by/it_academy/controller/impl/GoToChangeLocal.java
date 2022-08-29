package by.it_academy.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.it_academy.controller.Command;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToChangeLocal implements Command {
	
	private final static Logger LOG = LogManager.getLogger(GoToChangeLocal.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(AttributeAndParameter.LOCAL, request.getParameter(AttributeAndParameter.LOCAL));

		String url;
		url = (String) request.getSession(true).getAttribute(AttributeAndParameter.URL);
		if(url == null || url.isEmpty()) {
			LOG.warn("URL not found.");
			response.sendRedirect(JSPPageName.LIST_NEWS);
		}
		response.sendRedirect(url);

	}

}
