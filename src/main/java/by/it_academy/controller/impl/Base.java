package by.it_academy.controller.impl;

import java.io.IOException;

import by.it_academy.controller.Command;
import by.it_academy.controller.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Base implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(JSPPageName.BASE_PAGE).forward(request, response);
		
	}

}
