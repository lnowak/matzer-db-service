package com.matzer.db.api.rest.impl;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.matzer.db.api.dto.object.AccountDto;
import com.matzer.db.api.dto.response.DataContainer;
import com.matzer.db.api.dto.response.DataResponse;
import com.matzer.db.api.dto.response.Status;
import com.matzer.db.api.dto.response.StatusResponse;
import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.rest.IAccountService;
import com.matzer.db.api.security.AuthenticationException;
import com.matzer.db.api.security.principal.User;
import com.matzer.db.api.service.IAccountDtoService;
import com.matzer.db.enums.AccountTypeHelper;

/**
 * 
 * Implementation of the account service.
 * 
 * @author lkawon@gmail.com
 *
 */
public class AccountService implements IAccountService {

	/**
	 * Security context.
	 */
	@Context
	private SecurityContext securityContext;
	
	/**
	 * DTO service.
	 */
	@Autowired
	private IAccountDtoService accountDtoService;
	
	
	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#getAllAccounts(java.lang.String)
	 */
	@Override
	public final DataResponse<AccountDto> getAllAccounts(String token) {
		DataContainer<AccountDto> dataContainer = new DataContainer<AccountDto>(accountDtoService.getAllAccounts());
		return new DataResponse<AccountDto>(new Status(ErrorCode.OK), dataContainer);
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#getAccount(java.lang.String, java.lang.String)
	 */
	@Override
	public final DataResponse<AccountDto> getAccount(String token, String email) {
		verifyAccount(email);
		DataContainer<AccountDto> dataContainer = new DataContainer<AccountDto>(accountDtoService.getAccount(email));
		return new DataResponse<AccountDto>(new Status(ErrorCode.OK), dataContainer);
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#addAccount(com.matzer.db.api.dto.object.AccountDto)
	 */
	@Override
	public final StatusResponse addAccount(AccountDto account) {
		accountDtoService.addAccount(account);
		return new StatusResponse(new Status(ErrorCode.OK));
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#resendActivationEmail(java.lang.String)
	 */
	@Override
	public final StatusResponse resendActivationEmail(String email) {
		accountDtoService.resendActivationEmail(email);
		return new StatusResponse(new Status(ErrorCode.OK));
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#activateAccount(java.lang.String)
	 */
	@Override
	public final StatusResponse activateAccount(String activationCode) {
		accountDtoService.activateAccount(activationCode);
		return new StatusResponse(new Status(ErrorCode.OK));
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#generateResetPasswordCode(java.lang.String)
	 */
	@Override
	public final StatusResponse generateResetPasswordCode(String email) {
		accountDtoService.generateResetPasswordCode(email);
		return new StatusResponse(new Status(ErrorCode.OK));
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#resetPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public final StatusResponse resetPassword(String resetCode, String password) {
		accountDtoService.resetPassword(resetCode, password);
		return new StatusResponse(new Status(ErrorCode.OK));
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#updateAccount(java.lang.String, com.matzer.db.api.dto.object.AccountDto)
	 */
	@Override
	public final StatusResponse updateAccount(String token, AccountDto account) {
		verifyAccount(account.getEmail());
		accountDtoService.updateAccount(account);
		return new StatusResponse(new Status(ErrorCode.OK));
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.rest.IAccountService#deleteAccount(java.lang.String, java.lang.String)
	 */
	@Override
	public final StatusResponse deleteAccount(String token, String email) {
		verifyAccount(email);
		accountDtoService.deleteAccount(email);
		return new StatusResponse(new Status(ErrorCode.OK));
	}
	
	/**
	 * Checks if user can execute operation on given subject.
	 * 
	 * @param email			user email (equivalent of login)
	 */
	private void verifyAccount(String email) {
		if (securityContext.isUserInRole(AccountTypeHelper.USER)) {
			User user = (User) securityContext.getUserPrincipal();
			if (!user.getName().equals(email)) {
				throw new AuthenticationException(ErrorCode.FORBIDDEN);
			}
		}
	}
}
