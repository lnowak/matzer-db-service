package com.matzer.db.api.security;

/**
 * 
 * Interface for token authorization methods.
 * 
 * @author lkawon@gmail.com
 *
 */
public interface ITokenSecurityHandler {

	/**
	 * 
	 * Generate token and stores it by login as key.
	 * 
	 * @param login		login
	 * @return			
	 */
	public String getToken(String login, String password);
	
	/**
	 * Checks if token is valid.
	 * 
	 * @param token		token to check
	 * @return			true if valid or false if not valid
	 */
	public void validateToken(String token);
	
	/**
	 * Gets login assiociated with token and refersh expiration time.
	 * 
	 * @param token		token to refresh
	 * @return login associated with the token
	 */
	public String getLoginAndRefresh(String token);
}
