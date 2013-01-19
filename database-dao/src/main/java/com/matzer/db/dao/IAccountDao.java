package com.matzer.db.dao;

import com.matzer.db.entity.Account;

/**
 * 
 * DAO interface for account entity. 
 * 
 * @author lkawon@gmail.com
 *
 */
public interface IAccountDao extends IGenericDao<Account, Long> {
	
	/**
	 * Finds account by account email.
	 * 
	 * @param email			account email
	 * @return				account
	 */
	Account findByEmail(String email);
	
	/**
	 * Finds account by activation code.
	 * 
	 * @param activationCode	activation code
	 * @return					account
	 */
	Account findByActivationCode(String activationCode);
	
	/**
	 * Finds account by reset code.
	 * 
	 * @param resetCode			activation code
	 * @return					account
	 */
	Account findByResetCode(String resetCode);
}
