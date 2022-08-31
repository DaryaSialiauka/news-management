package by.it_academy.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.it_academy.dao.util.ConnectionPool;
import by.it_academy.dao.util.ConnectionPoolException;
import by.it_academy.util.NewsError;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ProjectContextListener implements ServletContextListener {
	
	private final static Logger LOG = LogManager.getLogger(ProjectContextListener.class);

	public void contextInitialized(ServletContextEvent event) {
		try {
			ConnectionPool.getInstance().initPoolData();
		} catch (ConnectionPoolException e) {
			
			LOG.error(e);
			throw new NewsError();
		} 
	}

	public void contextDestroyed(ServletContextEvent event) {
		ConnectionPool.getInstance().dispose(); 
	}
}
