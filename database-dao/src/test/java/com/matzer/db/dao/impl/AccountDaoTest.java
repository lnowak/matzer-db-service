package com.matzer.db.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.dbunit.DatabaseUnitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.matzer.db.commons.dbunit.AbstractDbUnitTests;
import com.matzer.db.dao.impl.AccountDao;
import com.matzer.db.entity.Account;
import com.matzer.db.enums.AccountType;

/**
 * 
 * Used for testing the {@link Account}.
 * 
 * @author lkawon@gmail.com
 *
 */
@ContextConfiguration(locations = { "classpath:spring-config-hsqldb.xml" })
@TransactionConfiguration(defaultRollback = false)
public class AccountDaoTest extends AbstractDbUnitTests {

	/**
	 * DAO for {@link Account}.
	 */
	@Autowired
	private AccountDao accountDao;
	
	
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
	 * Gets account by identifier.
	 */
	@Test
	public final void getAccountById() {
		// when
		Account account = accountDao.findById(1L);
		
		// then
		Assert.assertNotNull(account);
		Assert.assertEquals(account.getId(), new Long(1));
	}
	
	/**
	 * Adds new account.
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void addNewAccount() throws ParseException {
		// given
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Account account = new Account();
		account.setType(AccountType.ADMIN.getId());
		account.setFirstName("Jan");
		account.setLastName("Kowalski");
		account.setGender('M');
		account.setProvince("sląskie");
		account.setCity("Bielsko-Biała");
		account.setBirthDate(df.parse("1976-08-01"));
		account.setEmail("kowalski.jan@gmail.com");
		account.setPassword("password");
		account.setLanguage(0);
		account.setFacebookId(1234567890L);
		account.setCreationDate(df.parse("2012-10-10"));
		account.setModificationDate(df.parse("2012-10-10"));
		
		// when
		accountDao.save(account);
		
		// then
		Assert.assertTrue(account.getId() > 0);
		Assert.assertEquals(accountDao.findAll().size(), 2);		
	}
	
	/**
	 * Deletes account.
	 */
	@Test(dependsOnMethods = "addNewAccount")
	public final void deleteAccount() {
		// given
		Account account = accountDao.findByEmail("kowalski.jan@gmail.com");
		
		// when
		accountDao.delete(account);
		
		// then
		Assert.assertEquals(accountDao.findAll().size(), 1);
	}
}
