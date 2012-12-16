package com.matzer.db.api.rest.impl;

import com.matzer.db.api.dto.object.SecurityToken;
import com.matzer.db.api.dto.response.DataContainer;
import com.matzer.db.api.dto.response.DataResponse;
import com.matzer.db.api.dto.response.Status;
import com.matzer.db.api.dto.response.StatusResponse;
import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.rest.IAuthenticationService;
import com.matzer.db.api.security.ITokenSecurityHandler;

/**
 * 
 * Implementation of the authentication service.
 * 
 * @author lkawon@gmail.com
 *
 */
public class AuthenticationService implements IAuthenticationService {

	/**
	 * Security handler for tokens.
	 */
	private ITokenSecurityHandler tokenSecurityHandler;
	
	
	/**
	 * @return the tokenSecurityHandler
	 */
	public final ITokenSecurityHandler getTokenSecurityHandler() {
		return tokenSecurityHandler;
	}

	/**
	 * @param tokenSecurityHandler the tokenSecurityHandler to set
	 */
	public final void setTokenSecurityHandler(ITokenSecurityHandler tokenSecurityHandler) {
		this.tokenSecurityHandler = tokenSecurityHandler;
	}

	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.mycloud.rest.IAuthorizationService#getToken(java.lang.String, java.lang.String)
	 */
	@Override
	public final DataResponse<SecurityToken> getToken(String login, String password) {
		DataContainer<SecurityToken> dataContainer = new DataContainer<SecurityToken>(
				new SecurityToken(tokenSecurityHandler.getToken(login, password)));
		return new DataResponse<SecurityToken>(new Status(ErrorCode.OK), dataContainer);
	}

	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.mycloud.rest.IAuthorizationService#checkToken(java.lang.String)
	 */
	@Override
	public final StatusResponse checkToken(String token) {
		tokenSecurityHandler.validateToken(token);				
		return new StatusResponse(new Status(ErrorCode.OK));
	}
}
