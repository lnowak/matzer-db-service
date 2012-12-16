package com.matzer.db.api.dto.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.matzer.db.api.dto.object.AccountDto;
import com.matzer.db.api.dto.object.SecurityToken;

/**
 * 
 * Element for containing DTO data.
 * 
 * @author lkawon@gmail.com
 * 
 * @param <T>	class of the container
 *
 */
@XmlSeeAlso({ AccountDto.class, SecurityToken.class })
public class DataContainer<T> {

	/**
	 * List of DTO objects.
	 */	
	private List<T> array;
	
	/**
	 * DTO object. 
	 */
	private T object;
	
	
	/**
	 * Default constructor.
	 */
	public DataContainer() {
		
	}
	
	/**
	 * Constructor for DTO object.
	 * 
	 * @param object	DTO object
	 */
	public DataContainer(T object) {
		this.object = object;
	}
	
	/**
	 * Constructor for array of DTO objects.
	 * 
	 * @param array		DTO array
	 */
	public DataContainer(List<T> array) {
		this.array = array;
	}

	/**
	 * @return the array
	 */
	@XmlElement(name = "array")
	public final List<T> getArray() {
		return array;
	}

	/**
	 * @param array the array to set
	 */
	public final void setArray(List<T> array) {
		this.array = array;
	}

	/**
	 * @return the object
	 */
	@XmlElement(name = "object")
	public final T getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public final void setObject(T object) {
		this.object = object;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataContainer [");
		if (array != null) {
			builder.append("array=");
			builder.append(array);
			builder.append(", ");
		}
		if (object != null) {
			builder.append("object=");
			builder.append(object);
		}
		builder.append("]");
		return builder.toString();
	}
}
