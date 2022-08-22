package by.it_academy.controller.impl;

import java.io.IOException;

import by.it_academy.controller.Command;
import by.it_academy.util.AttributeAndParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SingOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		session.setAttribute(AttributeAndParameter.USER, AttributeAndParameter.USER_NOT_ACTIV);
		session.removeAttribute(AttributeAndParameter.ROLE);
		session.removeAttribute(AttributeAndParameter.USER_ID);
		String path = request.getContextPath() + "?";
		response.sendRedirect(path);

	}

}
