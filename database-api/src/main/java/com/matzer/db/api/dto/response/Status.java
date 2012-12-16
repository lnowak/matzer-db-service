package com.matzer.db.api.dto.response;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.matzer.db.api.error.ErrorCode;

/**
 * 
 * Status object containing the status itself and message.
 * 
 * @author lkawon@gmail.com
 *
 */
@XmlRootElement(name = "status")
@XmlType(propOrder = { "errorCode", "message" })
public class Status {
	
	/**
	 * Status code.
	 */
	private int errorCode;
	
	/**
	 * Message of the status.
	 */
	private String message;
	
	
	/**
	 * Default constructor.
	 */
	public Status() {
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param errorCode		code of the status
	 */
	public Status(ErrorCode errorCode) {
		this.errorCode = errorCode.getId();
		this.message = errorCode.getMessage();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param errorCode		code of the status
	 * @param message		status message
	 */
	public Status(ErrorCode errorCode, String message) {
		this.errorCode = errorCode.getId();
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public final int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public final void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Status [code=");
		builder.append(errorCode);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
}
