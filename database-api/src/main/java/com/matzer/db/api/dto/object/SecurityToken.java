package com.matzer.db.api.dto.object;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * DTO for security token.
 * 
 * @author lkawon@gmail.com
 *
 */
@XmlRootElement(name = "securityToken")
@XmlType(propOrder = { "token" })
public class SecurityToken {

	/**
	 * Token string.
	 */
	private String token;
	
	
	/**
	 * Default constructor.
	 */
	public SecurityToken() {
		
	}

	/**
	 * Constructor.
	 * 
	 * @param token	token string
	 */
	public SecurityToken(String token) {
		this.token = token;
	}
	
	/**
	 * @return the token
	 */
	public final String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public final void setToken(String token) {
		this.token = token;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecurityToken [token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}	
}
