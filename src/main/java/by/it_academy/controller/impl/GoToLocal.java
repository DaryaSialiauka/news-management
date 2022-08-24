package by.it_academy.controller.impl;

import java.io.IOException;

import by.it_academy.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute("local", request.getParameter("local"));

		response.sendRedirect((String) request.getSession(true).getAttribute("url"));

	}

}
