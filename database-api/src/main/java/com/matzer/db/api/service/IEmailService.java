package com.matzer.db.api.service;

import com.matzer.db.entity.Account;
import com.matzer.db.enums.EmailTemplateType;

/**
 * 
 * Service that allows to send email notifications.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public interface IEmailService {

	/**
	 * Used to send email from template.
	 * 
	 * @param type			type of the email
	 * @param account		account
	 */
	void sendEmail(EmailTemplateType type, Account account);
}
