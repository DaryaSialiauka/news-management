package by.it_academy.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.it_academy.controller.Command;
import by.it_academy.controller.JSPPageName;
import by.it_academy.util.Attribute;

public class GoToAuthentication implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute(Attribute.BODY, Attribute.SINGIN);
		request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);

	}

}
