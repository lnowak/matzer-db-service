package com.matzer.db.api.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.matzer.db.api.dto.object.AccountDto;
import com.matzer.db.api.service.IAccountDtoService;
import com.matzer.db.api.service.exception.ApiException;
import com.matzer.db.commons.dbunit.AbstractDbUnitTests;
import com.matzer.db.commons.utils.HashUtils;
import com.matzer.db.commons.utils.HashUtils.HashType;

/**
 * 
 * Class for testing the {@link MyCloudAccountDtoService}.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
@ContextConfiguration(locations = { "classpath:spring-config-jaxrs.xml", "classpath:spring-config-hsqldb.xml" })
@TransactionConfiguration
public class AccountDtoServiceTest extends AbstractDbUnitTests {
	
	/**
	 * DTO service for accounts.
	 */
	@Autowired
	private IAccountDtoService accountDtoService;
	

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
		getXmlDataSets().add("dbunit/email_template.xml");
		prepareDatabase();
	}
	
	/**
	 * Gets account the does not exist.
	 */
	@Test
	public final void getNonExistingAccountByEmail() {
		// when
		AccountDto account = accountDtoService.getAccount("unknown@gmail.com");
		
		// then
		Assert.assertNull(account);
	}
	
	/**
	 * Tests if the master account is present.
	 */
	@Test
	public final void getAccountByEmail() {
		// when
		AccountDto account = accountDtoService.getAccount("lkawon@gmail.com");
		
		// then
		Assert.assertNotNull(account);	
	}
	
	/**
	 * Adds new account.
	 */
	@Test
	public final void addNewAccount() {
		// when								
		AccountDto account = createAccountDto(true);
		accountDtoService.addAccount(account);
		
		// then
		AccountDto receivedAccount = accountDtoService.getAccount("lkawon@gmail.com");
		Assert.assertNotNull(receivedAccount);		
	}
	
	/**
	 * Resends activation email.
	 */
	@Test
	public final void resentActivationEmail() {
		// given							
		AccountDto account = createAccountDto(false);
		
		accountDtoService.addAccount(account);
		AccountDto receivedAccount = accountDtoService.getAccount(account.getEmail());
		
		// when
		accountDtoService.resendActivationEmail(receivedAccount.getEmail());	
	}
	
	/**
	 * Adds new account and activates it.
	 */
	@Test
	public final void activateAccount() {
		// given		
		AccountDto account = createAccountDto(false);
		accountDtoService.addAccount(account);
				
		AccountDto receivedAccount = accountDtoService.getAccount("login@test.pl");
		String activationCode = receivedAccount.getActivationCode();
		
		// when
		accountDtoService.activateAccount(activationCode);
		
		// then
		receivedAccount = accountDtoService.getAccount("login@test.pl");
		Assert.assertEquals(receivedAccount.getIsActive(), new Boolean(true));
		Assert.assertEquals(receivedAccount.getPassword(), HashUtils.generateHash(HashType.SHA, "password"));
	}
	
	/**
	 * Resets account password.
	 */
	@Test
	public final void resetAccountPassword() {
		// given				
		AccountDto account = createAccountDto(true);
		accountDtoService.addAccount(account);						
		
		// when
		AccountDto receivedAccount = accountDtoService.getAccount("login@test.pl");
		accountDtoService.generateResetPasswordCode(receivedAccount.getEmail());
		
		receivedAccount = accountDtoService.getAccount("login@test.pl");
		accountDtoService.resetPassword(receivedAccount.getResetCode(), "new_password");
		
		// then
		receivedAccount = accountDtoService.getAccount("login@test.pl");
		Assert.assertEquals(receivedAccount.getPassword(), HashUtils.generateHash(HashType.SHA, "new_password"));
	}
	
	/**
	 * Adds new account with already used login.
	 */
	@Test(expectedExceptions = ApiException.class, expectedExceptionsMessageRegExp = "Email is already used.")
	public final void addAccountWithUsedEmail() {		
		// when								
		AccountDto account = createAccountDto(true);
		accountDtoService.addAccount(account);
		accountDtoService.addAccount(account);
	}
	
	/**
	 * Adds account with empty DTO object.
	 */
	@Test(expectedExceptions = ApiException.class, expectedExceptionsMessageRegExp = "Received DTO object is empty.")
	public final void addAccountWithEmptyDtoObject() {
		// when
		accountDtoService.addAccount(null);
	}			
		
	/**
	 * Updates account by adding group.
	 */
	@Test
	public final void updateAccount() {
		// when
		AccountDto account = accountDtoService.getAccount("jan.kowalski@gmail.com");
		account.setCity("Bielsko-Biała");
		accountDtoService.updateAccount(account);
		
		// then
		AccountDto receivedAccount = accountDtoService.getAccount("jan.kowalski@gmail.com");
		Assert.assertNotNull(receivedAccount);		
		Assert.assertEquals(receivedAccount.getCity(), "Bielsko-Biała");
	}
	
	/**
	 * Updates non existing account.
	 */
	@Test(expectedExceptions = { ApiException.class }, expectedExceptionsMessageRegExp = "Entity not found in database.")
	public final void updateNotExistingAccount() {
		// when				
		AccountDto account = createAccountDto(true);	
		accountDtoService.updateAccount(account);
	}
	
	/**
	 * Updates account using empty DTO object.
	 */
	@Test(expectedExceptions = ApiException.class, expectedExceptionsMessageRegExp = "Received DTO object is empty.")
	public final void updateAccountWithEmptyDtoObject() {
		// when
		accountDtoService.updateAccount(null);
	}
	
	/**
	 * Deletes account.
	 */
	@Test
	public final void deleteAccount() {
		// given				
		AccountDto account = createAccountDto(true);
		accountDtoService.addAccount(account);
		
		account = accountDtoService.getAccount("login@test.pl");
		
		// when								
		accountDtoService.deleteAccount("login@test.pl");
		
		// then
		AccountDto receivedAccount = accountDtoService.getAccount("login");
		Assert.assertNull(receivedAccount);		
	}
		
	/**
	 * Deletes non existing account.
	 */
	@Test(expectedExceptions = { ApiException.class }, expectedExceptionsMessageRegExp = "Entity not found in database.")
	public final void deleteNonExistingAccount() {
		// given
		AccountDto account = createAccountDto(false);
		
		// when								
		accountDtoService.deleteAccount(account.getEmail());
	}
		
	/**
	 * Creates account DTO object.
	 * @return
	 */
	private AccountDto createAccountDto(boolean active) {
		AccountDto account = new AccountDto();
		account.setPassword("password");
		account.setEmail("login@test.pl");
		account.setIsActive(active);
		
		return account;
	}
}
