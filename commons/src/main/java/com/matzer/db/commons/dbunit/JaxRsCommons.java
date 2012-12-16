package com.matzer.db.commons.dbunit;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

/**
 * 
 * Commons class for helper methods for JaxRs testing in client mode.
 * 
 * @author lkawon@gmail.com
 *
 */
public class JaxRsCommons {

	/**
	 * Gets account service.
	 * 
	 * @return
	 */
	public static final <T> T getService(Class<T> clazz) {		
		return JAXRSClientFactory.create("http://localhost:8000", clazz);
	}
	
	/**
	 * Gets account service using basic authorization.
	 * 
	 * @return
	 */
	public static final <T> T getService(Class<T> clazz, String login, String password) {
		T service = JAXRSClientFactory.create("http://localhost:8000", clazz);		
		String authorizationHeader = "Basic " 
				+ org.apache.cxf.common.util.Base64Utility.encode((login + ":" + password).getBytes());
		WebClient.client(service).header("Authorization", authorizationHeader);
		
		return service;
	}
}
