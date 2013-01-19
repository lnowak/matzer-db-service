package com.matzer.db.api.rest.impl;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.matzer.db.api.dto.object.SecurityToken;
import com.matzer.db.api.dto.response.DataResponse;
import com.matzer.db.api.dto.response.StatusResponse;
import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.rest.IAuthenticationService;
import com.matzer.db.commons.dbunit.AbstractDbUnitTests;
import com.matzer.db.commons.dbunit.JaxRsCommons;

/**
 * 
 * Used for testing the {@link AuthenticationService}.
 * 
 * @author lkawon@gmail.com
 *
 */
@ContextConfiguration(locations = { "classpath:spring-config-jaxrs.xml", "classpath:spring-config-hsqldb.xml" })
@TransactionConfiguration
public class AuthenticationServiceTest extends AbstractDbUnitTests {
	
	/**
	 * Token length.
	 */
	private static final int TOKEN_LENGTH = 16;
	
	/**
	 * Token expiration time.
	 */
	private static final long TOKEN_EXPIRATION_TIME = 2000;
	
	/**
	 * Security token.
	 */
	private SecurityToken securityToken;
	
	
	/**
	 * Sets up the data for the test.
	 * 
	 * @throws DatabaseUnitException	
	 * @throws SQLException	
	 * @throws IOException	
	 */
	@BeforeClass
	public final void setDataSets() throws DatabaseUnitException, SQLException, IOException {
		getXmlDataSets().add("dbunit/account.xml");
		prepareDatabase();
	}
	
	/**
	 * Tries to get token using incorrect login.
	 */
	@Test
	public final void getTokenUsingIncorrectLogin() {		
		// given
		IAuthenticationService authorizationService = JaxRsCommons.getService(IAuthenticationService.class);
		
		// when
		DataResponse<SecurityToken> response = authorizationService.getToken("incorrect@gmail.com", "test");
		
		// then
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus().getErrorCode(), ErrorCode.INVALID_EMAIL_OR_PASSWORD.getId());
		Assert.assertEquals(response.getStatus().getMessage(), ErrorCode.INVALID_EMAIL_OR_PASSWORD.getMessage());
	}
	
	/**
	 * Tries to get token using incorrect password.
	 */
	@Test
	public final void getTokenUsingIncorrectPassword() {		
		// given
		IAuthenticationService authorizationService = JaxRsCommons.getService(IAuthenticationService.class);
		
		// when
		DataResponse<SecurityToken> response = authorizationService.getToken("admin@matzer.pl", "test");
		
		// then
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus().getErrorCode(), ErrorCode.INVALID_EMAIL_OR_PASSWORD.getId());
		Assert.assertEquals(response.getStatus().getMessage(), ErrorCode.INVALID_EMAIL_OR_PASSWORD.getMessage());
	}
	
	/**
	 * Gets token.
	 */
	@Test
	public final void getToken() {			
		// given
		IAuthenticationService authorizationService = JaxRsCommons.getService(IAuthenticationService.class);
					
		// when
		DataResponse<SecurityToken> response = authorizationService.getToken("admin@matzer.pl", "password");		
		
		// then
		Assert.assertNotNull(response);
		
		securityToken = response.getDataContainer().getObject();
		Assert.assertNotNull(securityToken);
		Assert.assertNotNull(securityToken.getToken());
		Assert.assertEquals(securityToken.getToken().length(), TOKEN_LENGTH);		
	}
	
	/**
	 * Checks incorrect token.
	 */
	@Test
	public final void checkIncorrectToken() {
		// given
		IAuthenticationService authorizationService = JaxRsCommons.getService(IAuthenticationService.class);
						
		// when
		StatusResponse response = authorizationService.checkToken("123123123");
		
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus().getErrorCode(), ErrorCode.INVALID_SECURITY_TOKEN.getId());
		Assert.assertEquals(response.getStatus().getMessage(), ErrorCode.INVALID_SECURITY_TOKEN.getMessage());
	}
	
	/**
	 * Checks correct token.
	 */
	@Test(dependsOnMethods = "getToken")
	public final void checkToken() {
		// given
		IAuthenticationService authorizationService = JaxRsCommons.getService(IAuthenticationService.class);
						
		// when
		StatusResponse response = authorizationService.checkToken(securityToken.getToken());
		
		// then
		Assert.assertNotNull(response);		
		Assert.assertEquals(response.getStatus().getErrorCode(), ErrorCode.OK.getId());		
	}
	
	/**
	 * Checks correct token.
	 * @throws InterruptedException 
	 */
	@Test(dependsOnMethods = "getToken")
	public final void checkToken2() throws InterruptedException {
		// given
		IAuthenticationService authorizationService = JaxRsCommons.getService(IAuthenticationService.class);
						
		// when
		Thread.sleep(TOKEN_EXPIRATION_TIME);
		StatusResponse response = authorizationService.checkToken(securityToken.getToken());
		
		// then
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus().getErrorCode(), ErrorCode.INVALID_SECURITY_TOKEN.getId());
		Assert.assertEquals(response.getStatus().getMessage(), ErrorCode.INVALID_SECURITY_TOKEN.getMessage());
	}
}
