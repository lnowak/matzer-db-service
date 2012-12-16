package com.matzer.db.enums;

import javax.annotation.security.RolesAllowed;

/**
 * 
 * Helps with setting {@link RolesAllowed} annotations.
 * 
 * @author lkawon@gmail.com
 *
 */
public class AccountTypeHelper {

	/**
	 * Administrator.
	 */
	public static final String ADMIN = "ROLE_ADMIN";
	
	/**
	 * User.
	 */
	public static final String USER = "ROLE_USER";
}
