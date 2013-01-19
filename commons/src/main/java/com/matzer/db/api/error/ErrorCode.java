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
	 * DTO object is empty.
	 */
	EMPTY_DTO_OBJECT					(100, "Received DTO object is empty."),
	
	/**
	 * Email is already used.
	 */
	EMAIL_TAKEN							(101, "Email is already used."),
	
	/**
	 * Account was not found.
	 */
	ACCOUNT_WAS_NOT_FOUND				(102, "Account was not found."),
	
	/**
	 * Activation code is incorrect.
	 */
	ACTIVATION_CODE_IS_INCORRECT		(103, "Activation code is incorrect."),
	
	/**
	 * Activation code is empty.
	 */
	ACTIVATION_CODE_IS_EMPTY			(104, "Activation code is empty."),
	
	/**
	 * Entity not found.
	 */
	ENTITY_NOT_FOUND					(105, "Entity not found in database."),
	
	/**
	 * Missing parameter.
	 */
	MISSING_PARAMETER					(106, "Missing parameter."),
	
	/**
	 * Email template was not found.
	 */
	EMAIL_TEMPLATE_WAS_NOT_FOUND		(107, "Email template was not found."),
	
	/**
	 * Missing email client credentials.
	 */
	MISSING_EMAIL_CLIENT_CREDENTIALS	(200, "Missing email client credentials."),
	
	/**
	 * Incorrect email adderss.
	 */
	INCORRECT_EMAIL_ADDRESS				(201, "Incorrect email adress."),
	
	/**
	 * Unknown error in SMTP client.
	 */
	EMAIL_CLIENT_UNKNOWN_ERROR			(202, "Unknown error with SMTP client."),
	
	/**
	 * Missing email client configuration.
	 */
	MISSING_EMAIL_CLIENT_CONFIGURATION	(203, "Missing email client configuration."),
	
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
	 * Invalid email or password.
	 */
	INVALID_EMAIL_OR_PASSWORD			(601, "Invalid email or password."),
	
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
