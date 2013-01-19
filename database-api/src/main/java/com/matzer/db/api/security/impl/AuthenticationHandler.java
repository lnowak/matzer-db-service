package com.matzer.db.api.security.impl;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.security.auth.Subject;

import org.apache.cxf.interceptor.security.RolePrefixSecurityContextImpl;
import org.apache.cxf.security.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.security.AuthenticationException;
import com.matzer.db.api.security.IAuthenticationHandler;
import com.matzer.db.api.security.principal.Role;
import com.matzer.db.api.security.principal.User;
import com.matzer.db.dao.IAccountDao;
import com.matzer.db.entity.Account;
import com.matzer.db.enums.AccountType;

/**
 * 
 * Implements authentication process for my cloud services.
 * 
 * @author lkawon@gmail.com
 *
 */
public class AuthenticationHandler implements IAuthenticationHandler {
	
	/**
	 * Prefix for role.
	 */
	private static final String ROLE_PREFIX = "ROLE_";
	
	/**
	 * DAO for accounts.
	 */
	@Autowired
	private IAccountDao accountDao;
	
	
	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.security.IAuthenticationHandler#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	public final void authenticate(String email, String password) {		
		Account account = accountDao.findByEmail(email);
		if (account != null) {
			if (!account.getPassword().equals(password) || (account.getIsActive() == null || !account.getIsActive())) {
				throw new AuthenticationException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
			}
		} else {
			throw new AuthenticationException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.security.IAuthenticationHandler#createSecurityContext(java.lang.String)
	 */
	@Override
	public final SecurityContext createSecurityContext(String email) {
		Account account = accountDao.findByEmail(email);
		if (account != null) {
			Set<Principal> principals = new HashSet<Principal>();
			
			User user = new User(account.getEmail());
			user.setId(account.getId());				
			principals.add(user);
						
			AccountType accountType = AccountType.find(account.getType());
			Role role = new Role(0, accountType.getName());
			principals.add(role);			
	    	
	    	Subject subject = new Subject(true, principals, new HashSet<String>(), new HashSet<String>());
	    	return new RolePrefixSecurityContextImpl(subject, ROLE_PREFIX);			
		} else {
			throw new AuthenticationException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
		}		
	}
}
