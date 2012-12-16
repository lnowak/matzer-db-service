package com.matzer.db.api.security;

import org.apache.cxf.security.SecurityContext;

/**
 * 
 * Used to do the actual authentication.
 * 
 * @author lkawon@gmail.com
 *
 */
public interface IAuthenticationHandler {

	/**
	 * Verifies the login and password.
	 * 
	 * @param login					login
	 * @param password				password
	 * @throws SecurityException	
	 */
	void authenticate(String login, String password);
	
	/**
	 * Creates security context.
	 * 
	 * @param login					login
	 * @return security context
	 */
	SecurityContext createSecurityContext(String login);
}
