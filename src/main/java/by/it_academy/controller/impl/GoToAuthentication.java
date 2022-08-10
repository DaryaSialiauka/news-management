package by.it_academy.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import by.it_academy.controller.Command;
import by.it_academy.util.AttributeAndParameter;
import by.it_academy.util.JSPPageName;

public class GoToAuthentication implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		session.removeAttribute(AttributeAndParameter.USER_BEAN);
		request.getRequestDispatcher(JSPPageName.BASE_PAGE+"?"+AttributeAndParameter.BODY + AttributeAndParameter.EQUALS + AttributeAndParameter.SINGIN).forward(request, response);

	}

}
