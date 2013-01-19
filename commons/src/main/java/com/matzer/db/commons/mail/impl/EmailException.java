package com.matzer.db.commons.mail.impl;

import com.matzer.db.api.error.ErrorCode;

/**
 * 
 * Exception thrown when security problem occurred.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public class EmailException extends RuntimeException {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 123128613423442312L;
	
	/**
	 * Error code.
	 */
	private ErrorCode errorCode;
		
	
	/**
	 * Constructor.
	 * 
	 * @param errorCode		error code
	 */
	public EmailException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;	
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message	message of the exception
	 */
	public EmailException(ErrorCode errorCode, String message) {		
		super(message);
		this.errorCode = errorCode;
	}
	
	public EmailException(ErrorCode errorCode, Exception cause) {
		super(errorCode.getMessage(), cause);
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
