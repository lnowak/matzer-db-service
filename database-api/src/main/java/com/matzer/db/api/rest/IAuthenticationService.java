package com.matzer.db.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.matzer.db.api.dto.object.SecurityToken;
import com.matzer.db.api.dto.response.DataResponse;
import com.matzer.db.api.dto.response.StatusResponse;
import com.matzer.db.api.security.Unsecured;

/**
 * 
 * Interface for user authentication.
 * 
 * @author lkawon@gmail.com
 *
 */
@Unsecured
@Path("/authentication/")
@Produces(MediaType.APPLICATION_JSON)
public interface IAuthenticationService {

	/**
	 * Generates authentication token.
	 * 
	 * @param login		login name
	 * @param password	password
	 * @return			response with status and authentication token
	 */
	@GET
	@Path("/getToken")
	DataResponse<SecurityToken> getToken(@QueryParam("email") String email, @QueryParam("password") String password);
	
	/**
	 * Checks the token if it is still alive.
	 * 
	 * @param token
	 * @return			status with response code
	 */
	@GET
	@Path("/checkToken") 
	StatusResponse checkToken(@QueryParam("token") String token);
}
