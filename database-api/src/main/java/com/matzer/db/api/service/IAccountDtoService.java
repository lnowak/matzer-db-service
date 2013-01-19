package com.matzer.db.api.service;

import java.util.List;

import com.matzer.db.api.dto.object.AccountDto;

/**
 * 
 * Service for managing the account DTO objects.
 * 
 * @author lkawon@gmail.com
 *
 */
public interface IAccountDtoService {

	/**
	 * Gets all users.
	 * 
	 * @return		list of accounts
	 */
	List<AccountDto> getAllAccounts();
	
	/**
	 * Gets user with given email.
	 * 
	 * @param email		email
	 * @return			found account or null
	 */
	AccountDto getAccount(String email);
	
	/**
	 * Adds new account.
	 * 
	 * @param accountDto	account to add
	 */
	void addAccount(AccountDto accountDto);
	
	/**
	 * Resends activation login notification.
	 * 
	 * @param email			email
	 */
	void resendActivationEmail(String email);
	
	/**
	 * Activates account with activation code and sets password.
	 * 
	 * @param activationCode	activation code
	 */
	void activateAccount(String activationCode);
	
	/**
	 * Reset password to new one that will be generated randomly and sent by email.
	 * 
	 * @param email			email
	 */
	void generateResetPasswordCode(String email);

	/**
	 * Accepts reset operation.
	 * 
	 * @param resetCode		reset code
	 * @param password		password to sets
	 */
	void resetPassword(String resetCode, String password);
	
	/**
	 * Updates account.
	 * 
	 * @param accountDto	account to update
	 */
	void updateAccount(AccountDto accountDto);
	
	/**
	 * Deletes account.
	 * 
	 * @param id		identifier of the account
	 * @return			response with status
	 */
	void deleteAccount(String email);
}
