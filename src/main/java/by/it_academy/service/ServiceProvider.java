package by.it_academy.service;

import by.it_academy.service.impl.NewsServiceImpl;
import by.it_academy.service.impl.UserServiceImpl;

public class ServiceProvider {

	private final static ServiceProvider instance = new ServiceProvider();

	private final static UserService userService = new UserServiceImpl();
	
	private final static NewsService newsService = new NewsServiceImpl();

	private ServiceProvider() {

	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}
	
	public NewsService getNewsService() {
		return newsService;
	}

}
