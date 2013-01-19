package com.matzer.db.api;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * Main class of the project.
 * 
 * @author lkawon@gmail.com
 *
 */
public class Main {

	/**
	 * Log4j logger.
	 */
	private static final Logger LOG = Logger.getLogger(Main.class);
	
	
	/**
	 * Starts the service by initializing spring context.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {	
		if (args.length == 1) {		
			LOG.info("Encoding: " + System.getProperty("file.encoding") + ".");
			LOG.info("Initializing application...");
			new ClassPathXmlApplicationContext("classpath:spring-config-jaxrs.xml", "classpath:" + args[0]);
			LOG.info("Application initialized.");
		} else {
			LOG.error("Missing spring configuration file.");
		}
	}
}
