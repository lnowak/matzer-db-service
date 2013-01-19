package com.matzer.db.api.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.matzer.db.api.dto.object.AccountDto;
import com.matzer.db.api.dto.response.DataResponse;
import com.matzer.db.api.dto.response.StatusResponse;
import com.matzer.db.api.security.Unsecured;
import com.matzer.db.enums.AccountTypeHelper;

/**
 * 
 * Interface for user management.
 * 
 * @author lkawon@gmail.com
 *
 */
@Path("/account/")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ AccountTypeHelper.ADMIN })
public interface IAccountService {	
	
	/**
	 * Gets all users.
	 * 
	 * @return			response with status and data
	 */
	@GET
	@Path("/getAllAccounts")	
	DataResponse<AccountDto> getAllAccounts(@QueryParam("token") String token);
	
	/**
	 * Gets user with given email.
	 * 
	 * @param token		security token
	 * @param email		email
	 * @return			response with data and status
	 */
	@GET
	@Path("/getAccount")	
	@RolesAllowed({ AccountTypeHelper.USER })
	DataResponse<AccountDto> getAccount(@QueryParam("token") String token, @QueryParam("email") String email);
	
	/**
	 * Adds new account.
	 * 
	 * @param account	account to add	
	 * @return			response with status
	 */
	@PUT
	@Path("/addAccount")
	@Unsecured
	StatusResponse addAccount(AccountDto account);
	
	/**
	 * Resends activation login notification.
	 * 
	 * @param email			email
	 * @return				response with status
	 */
	@GET
	@Path("/resendActivationEmail")
	@Unsecured
	StatusResponse resendActivationEmail(@QueryParam("email") String email);
	
	/**
	 * Activates account with activation code and sets password.
	 * 
	 * @param activationCode	activation code
	 * @return					response with status
	 */
	@GET
	@Path("/activateAccount")
	@Unsecured
	StatusResponse activateAccount(@QueryParam("activationCode") String activationCode);
	
	/**
	 * Reset password to new one that will be generated randomly and sent by email.
	 * 
	 * @param email			email
	 * @return				response with status
	 */
	@GET
	@Path("/generateResetPasswordCode")
	@Unsecured
	StatusResponse generateResetPasswordCode(@QueryParam("email") String email);

	/**
	 * Accepts reset operation.
	 * 
	 * @param resetCode		reset code
	 * @param password		password to set
	 * @return				response with status
	 */
	@GET
	@Path("/resetPassword")
	@Unsecured
	StatusResponse resetPassword(@QueryParam("resetCode") String resetCode, @QueryParam("password") String password);
	
	/**
	 * Updates account.
	 * 
	 * @param account	account to update
	 * @return			response with status
	 */
	@POST
	@Path("/updateAccount")
	@RolesAllowed({ AccountTypeHelper.USER })
	StatusResponse updateAccount(@QueryParam("token") String token, AccountDto account);
	
	/**
	 * Deletes account.
	 * 
	 * @param id		identifier od the account
	 * @return			response with status
	 */
	@DELETE
	@Path("/deleteAccount")
	@RolesAllowed({ AccountTypeHelper.USER })
	StatusResponse deleteAccount(@QueryParam("token") String token, @QueryParam("email") String email);
}
