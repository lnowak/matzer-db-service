package com.matzer.db.api.error;

/**
 * 
 * Error codes for REST operations.
 * 
 * @author lkawon@gmail.com
 *
 */
public enum ErrorCode {

	/**
	 * Operation successfully processed.
	 */
	OK									(0, null),
	
	/**
	 * The resource is forbidden.
	 */
	FORBIDDEN							(403, "Forbidden."),
	
	/**
	 * The requested resource was not found.
	 */
	NOT_FOUND							(404, "The requested resource was not found"),
	
	/**
	 * Security token is invalid or expired.
	 */
	INVALID_SECURITY_TOKEN				(600, "Security token is invalid or expired."),
	
	/**
	 * Invalid email.
	 */
	INVALID_EMAIL						(601, "Invalid email."),
	
	/**
	 * Invalid password.
	 */
	INVALID_PASSWORD					(602, "Invalid password."),
	
	/**
	 * Missing authentication token.
	 */
	MISSING_AUTHENTICATION_TOKEN		(603, "Missing security token."),
	
	/**
	 * Missing authentication handler.
	 */
	MISSING_AUTHENTICATION_HANDLER		(650, "Missing authentication handler."),
	
	/**
	 * Unknown error.
	 */
	UNKNOWN								(999, "Unknown error.");
	
	
	/**
	 * Identifier of the code.
	 */
	private int id;
	
	/**
	 * Message for the code.
	 */
	private String message;
	
	
	/**
	 * Constructor.
	 * 
	 * @param id		code identifier
	 * @param message	message
	 */
	private ErrorCode(int id, String message) {
		this.id = id;
		this.message = message;
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public final void setMessage(String message) {
		this.message = message;
	}
}
