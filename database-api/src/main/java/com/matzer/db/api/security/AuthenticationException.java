package com.matzer.db.api.security;

import com.matzer.db.api.error.ErrorCode;

/**
 * 
 * Exception thrown when security problem occurred.
 * 
 * @author lkawon@gmail.com
 *
 */
public class AuthenticationException extends RuntimeException {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 7883286136813942312L;
	
	/**
	 * Error code.
	 */
	private ErrorCode errorCode;
		
	
	/**
	 * Constructor.
	 * 
	 * @param errorCode		error code
	 */
	public AuthenticationException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;	
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message	message of the exception
	 */
	public AuthenticationException(ErrorCode errorCode, String message) {		
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public final ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public final void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
