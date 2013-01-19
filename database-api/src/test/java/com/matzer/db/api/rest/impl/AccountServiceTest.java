package com.matzer.db.api.rest.impl;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.matzer.db.api.dto.object.AccountDto;
import com.matzer.db.api.dto.object.SecurityToken;
import com.matzer.db.api.dto.response.DataResponse;
import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.rest.IAccountService;
import com.matzer.db.api.rest.IAuthenticationService;
import com.matzer.db.commons.dbunit.AbstractDbUnitTests;
import com.matzer.db.commons.dbunit.JaxRsCommons;

/**
 * 
 * Class for testing the REST api for {@link IMyCloudAccountService}.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
@ContextConfiguration(locations = { "classpath:spring-config-jaxrs.xml", "classpath:spring-config-hsqldb.xml" })
@TransactionConfiguration
public class AccountServiceTest extends AbstractDbUnitTests {
	
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
	 * Gets security token.
	 */
	@Test
	public final void getSecurityToken() {
		// given
		IAuthenticationService authenticationService = JaxRsCommons.getService(IAuthenticationService.class);
		
		// when
		DataResponse<SecurityToken> response = authenticationService.getToken("admin@matzer.pl", "password");
		
		// then
		Assert.assertNotNull(response);
		securityToken = response.getDataContainer().getObject();
		Assert.assertNotNull(securityToken);
	}
	
	/**
	 * Tries to get account with incorrect login.
	 */
	@Test
	public final void getAccountWithIncorrectToken() {		
		// given
		IAccountService accountService = JaxRsCommons.getService(IAccountService.class);
		
		// when
		DataResponse<AccountDto> response = accountService.getAccount("test", "admin@matzer.pl");
		
		// then
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus().getErrorCode(), ErrorCode.INVALID_SECURITY_TOKEN.getId());
		Assert.assertEquals(response.getStatus().getMessage(), ErrorCode.INVALID_SECURITY_TOKEN.getMessage());
	}
	
	/**
	 * Tries to get account with incorrect login.
	 */
	@Test(dependsOnMethods = "getSecurityToken")
	public final void getAccount() {		
		// given
		IAccountService accountService = JaxRsCommons.getService(IAccountService.class);
		
		// when
		DataResponse<AccountDto> response = accountService.getAccount(securityToken.getToken(), "admin@matzer.pl");
		
		// then
		Assert.assertEquals(response.getStatus().getErrorCode(), ErrorCode.OK.getId());
		Assert.assertNotNull(response.getDataContainer().getObject());
	}
	
	/**
	 * Tries to invoke method on interface without permissions.
	 */
	@Test
	public final void invokeMethodWithUserWithoutPermissions() {
		// given		
		IAuthenticationService authenticationService = JaxRsCommons.getService(IAuthenticationService.class);	
		IAccountService accountService = JaxRsCommons.getService(IAccountService.class);
		DataResponse<SecurityToken> tokenResponse = authenticationService.getToken("jan.kowalski@gmail.com", "password");
		String token = tokenResponse.getDataContainer().getObject().getToken();
		
		// when
		DataResponse<AccountDto> accountResponse = accountService.getAccount(token, "lkawon@gmail.com");
		
		// then
		Assert.assertNotNull(accountResponse);
		Assert.assertNull(accountResponse.getDataContainer());
		Assert.assertEquals(accountResponse.getStatus().getErrorCode(), ErrorCode.FORBIDDEN.getId());
	}
	
	/**
	 * Tries to invoke method on interface without permissions (2).
	 */
	@Test
	public final void invokeMethodWithUserWithoutPermissions2() {
		// given		
		IAuthenticationService authenticationService = JaxRsCommons.getService(IAuthenticationService.class);	
		IAccountService accountService = JaxRsCommons.getService(IAccountService.class);
		DataResponse<SecurityToken> tokenResponse = authenticationService.getToken("jan.kowalski@gmail.com", "password");
		String token = tokenResponse.getDataContainer().getObject().getToken();
		
		// when
		DataResponse<AccountDto> accountResponse = accountService.getAllAccounts(token);
		
		// then
		Assert.assertNotNull(accountResponse);
		Assert.assertNull(accountResponse.getDataContainer());
		Assert.assertEquals(accountResponse.getStatus().getErrorCode(), ErrorCode.FORBIDDEN.getId());	
	}
	
	/**
	 * Tries to invoke method on interface without permissions (2).
	 */
	@Test
	public final void invokeMethodWithNotActiveUser() {
		IAuthenticationService authenticationService = JaxRsCommons.getService(IAuthenticationService.class);	
		DataResponse<SecurityToken> tokenResponse = authenticationService.getToken("not_active@gmail.com", "password");
		
		// then
		Assert.assertNotNull(tokenResponse);
		Assert.assertNull(tokenResponse.getDataContainer());
		Assert.assertEquals(tokenResponse.getStatus().getErrorCode(), ErrorCode.INVALID_EMAIL_OR_PASSWORD.getId());
		
	}
}
