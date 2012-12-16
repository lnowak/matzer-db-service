package com.matzer.db.api.dto.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Response sent to the user.
 * 
 * @author lkawon@gmail.com
 * 
 */
@XmlRootElement(name = "response")
@XmlType(propOrder = { "status" })
public class StatusResponse {

	/**
	 * Status of the response.
	 */
	private Status status;		
	
	
	/**
	 * Default constructor.
	 */
	public StatusResponse() {
		
	}
	
	/**
	 * 
	 * Constructor.
	 * 
	 * @param status			status of the response
	 */
	public StatusResponse(Status status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	@XmlElement(name = "status")
	public final Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(Status status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}
