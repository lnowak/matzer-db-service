package com.matzer.db.api.service.exception;

import com.matzer.db.api.error.ErrorCode;

/**
 * 
 * Exception thrown when passed DTO object is empty.
 * 
 * @author lkawon@gmail.com
 *
 */
public class ApiException extends RuntimeException {

	/**
	 * Automatically generated.
	 */
	private static final long serialVersionUID = -5910066937070113240L;

	/**
	 * Error code.
	 */
	private ErrorCode errorCode;
		
	
	/**
	 * Constructor.
	 * 
	 * @param errorCode		error code
	 */
	public ApiException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;	
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message	message of the exception
	 */
	public ApiException(ErrorCode errorCode, String message) {		
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
