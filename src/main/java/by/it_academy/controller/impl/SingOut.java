package by.it_academy.controller.impl;

import java.io.IOException;

import by.it_academy.controller.Command;
import by.it_academy.controller.JSPPageName;
import by.it_academy.util.Attribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SingOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(Attribute.USER, Attribute.USER_NOT_ACTIV);
		request.getSession(true).removeAttribute(Attribute.ROLE);
		request.removeAttribute(Attribute.ERROR_AUTHENTICATION);
		request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);
		
	}

	
}
