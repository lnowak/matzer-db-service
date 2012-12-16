package com.matzer.db.api.security.principal;

import java.security.Principal;

/**
 * 
 * Represents user.
 * 
 * @author lkawon@gmail.com
 *
 */
public class User implements Principal {
	
	/**
	 * User identifier.
	 */
	private long id;
	
	/**
	 * Name of the user.
	 */
	private String name;
	
	/**
	 * Identifier of the group.
	 */
	private long groupId;
	
	
	/**
	 * Constructor.
	 * 
	 * @param name		name of the user	 
	 **/
	public User(String name) {
		this.name = name;
	}	

	/**
	 * @return the id
	 */
	public final long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the groupId
	 */
	public final long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public final void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append("]");
		return builder.toString();
	}
}
