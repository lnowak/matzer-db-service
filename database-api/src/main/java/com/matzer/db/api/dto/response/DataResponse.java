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
 * @param <T>
 */
@XmlRootElement(name = "response")
@XmlType(propOrder = { "status", "dataContainer" })
public class DataResponse<T> {

	/**
	 * Status of the response.
	 */
	private Status status;
	
	/**
	 * Container for DTO objects.
	 */
	private DataContainer<T> dataContainer;
	
	
	/**
	 * Default constructor.
	 */
	public DataResponse() {
		
	}
	
	/**
	 * 
	 * Constructor.
	 * 
	 * @param status			status of the response
	 * @param dataContainer		DTO objects container
	 */
	public DataResponse(Status status, DataContainer<T> dataContainer) {
		this.status = status;
		this.dataContainer = dataContainer;
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

	/**
	 * @return the dataContainer
	 */
	@XmlElement(name = "data")
	public final DataContainer<T> getDataContainer() {
		return dataContainer;
	}

	/**
	 * @param dataContainer the dataContainer to set
	 */
	public final void setDataContainer(DataContainer<T> dataContainer) {
		this.dataContainer = dataContainer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [status=");
		builder.append(status);
		builder.append(", dataContainer=");
		builder.append(dataContainer);
		builder.append("]");
		return builder.toString();
	}
}
