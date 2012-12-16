package com.matzer.db.api.security.principal;

import java.security.Principal;

/**
 * 
 * Represents role.
 * 
 * @author lkawon@gmail.com
 *
 */
public class Role implements Principal {

	/**
	 * Role identifier.
	 */
	private long roleId;
	
	/**
	 * Role of the user.
	 */
	private String role;
	
	
	/**
	 * Constructor.
	 * 
	 * @param role	role
	 */
	public Role(String role) {
		this.role = role;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param roleId	role identifier
	 * @param role		role name
	 */
	public Role(long roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}
	
	/**
	 * @return the roleId
	 */
	public final long getRoleId() {
		return roleId;
	}

	/* (non-Javadoc)
	 * @see java.security.Principal#getName()
	 */
	@Override
	public final String getName() {		
		return role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [roleId=");
		builder.append(roleId);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}
}
