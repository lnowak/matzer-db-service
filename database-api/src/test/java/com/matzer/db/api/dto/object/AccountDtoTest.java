package com.matzer.db.api.dto.object;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.matzer.db.entity.Account;
import com.matzer.db.enums.AccountType;

/**
 * 
 * For testing the {@link AccountDto} object.
 * 
 * @author lkawon@gmail.com
 *
 */
@ContextConfiguration(locations = { "classpath:spring-config-jaxrs.xml", "classpath:spring-config-hsqldb.xml" })
@TransactionConfiguration
public class AccountDtoTest extends AbstractTransactionalTestNGSpringContextTests {

	/**
	 * Dozer mapper.
	 */
	@Autowired
	private Mapper mapper;
	
	/**
	 * Entity object.
	 */
	private Account account;
	
	/**
	 * DTO object/
	 */
	private AccountDto accountDto;
	
	
	/**
	 * Converts account to DTO object.
	 */
	@Test
	public final void convertToDto() {
		// given
		account = new Account();
		account.setBirthDate(new Date());
		account.setCity("Kraków");
		account.setCreationDate(new Date());
		account.setEmail("lkawon@gmail.com");
		account.setFacebookId(123456789L);
		account.setFirstName("Łukasz");
		account.setGender('M');
		account.setId(1L);
		account.setLastName("Nowak");
		account.setModificationDate(new Date());
		account.setPassword("password");
		account.setLanguage(0);
		account.setProvince("małopolskie");
		account.setType(AccountType.USER.getId());		
		account.setIsActive(new Boolean(true));
		account.setActivationCode("1234567890");
		account.setResetCode("1234567890");
		
		// when								
		accountDto = mapper.map(account, AccountDto.class);
		
		// then
		Assert.assertNotNull(accountDto);
		Assert.assertEquals(accountDto.getBirthDate(), account.getBirthDate());
		Assert.assertEquals(accountDto.getCity(), account.getCity());
		Assert.assertEquals(accountDto.getCreationDate(), account.getCreationDate());
		Assert.assertEquals(accountDto.getEmail(), account.getEmail());
		Assert.assertEquals(accountDto.getFacebookId(), account.getFacebookId());
		Assert.assertEquals(accountDto.getFirstName(), account.getFirstName());
		Assert.assertEquals(accountDto.getGender(), account.getGender());
		Assert.assertEquals(accountDto.getId(), account.getId());
		Assert.assertEquals(accountDto.getLastName(), account.getLastName());
		Assert.assertEquals(accountDto.getModificationDate(), account.getModificationDate());
		Assert.assertEquals(accountDto.getPassword(), account.getPassword());
		Assert.assertEquals(accountDto.getLanguage(), account.getLanguage());
		Assert.assertEquals(accountDto.getProvince(), account.getProvince());
		Assert.assertEquals(accountDto.getType(), account.getType());
		Assert.assertEquals(accountDto.getActivationCode(), account.getActivationCode());
		Assert.assertEquals(accountDto.getResetCode(), account.getResetCode());
	}
	
	/**
	 * Converts from DTO object.
	 */
	@Test(dependsOnMethods = "convertToDto")
	public final void convertFromDto() {
		// when
		Account convertedAccount = mapper.map(accountDto, Account.class);
		
		// then
		Assert.assertEquals(convertedAccount.toString(), account.toString());				
	}
}
