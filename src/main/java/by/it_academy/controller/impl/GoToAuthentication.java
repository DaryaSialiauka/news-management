package by.it_academy.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.it_academy.controller.Command;
import by.it_academy.util.Attribute;
import by.it_academy.util.JSPPageName;

public class GoToAuthentication implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(JSPPageName.BASE_PAGE+"?"+Attribute.BODY + Attribute.EQUALS + Attribute.SINGIN).forward(request, response);

	}

}
