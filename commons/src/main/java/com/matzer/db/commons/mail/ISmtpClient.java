package com.matzer.db.commons.mail;

/**
 * 
 * Interface used to send email.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public interface ISmtpClient {

	/**
	 * Sends email using SMTP protocol.
	 * 
	 * @param recipients		list of recipients
	 * @param subject			subject of the mail
	 * @param text				text of the mail
	 * @param isHtml			send as HTML mail
	 */
	void sendEmail(String recipients, String subject, String text, boolean isHtml);
}
