package com.matzer.db.commons.mail.impl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.matzer.db.commons.mail.ISmtpClient;

/**
 * 
 * Used for testing the {@link SmtpClient}.
 * 
 * @author lkawon@gmail.com
 *
 */
public class SmtpClientTest {

	/**
	 * Email client interface.
	 */
	private ISmtpClient emailClient;
	
	
	/**
	 * Sets up email client object.
	 */
	@BeforeClass
	public final void setUp() {
		emailClient = new SmtpClient();
		
		((SmtpClient) emailClient).setSmtpHost("smtp.gmail.com");
		((SmtpClient) emailClient).setSmtpPort(465);
		((SmtpClient) emailClient).setUsername("wihomemasterlib");
		((SmtpClient) emailClient).setPassword("Homer123");
		((SmtpClient) emailClient).setFrom("wihomemasterlib@gmail.com");
	}
	
	/**
	 * Tries to send text email.
	 */
	@Test
	public final void sendTextEmail() {
		// when
		emailClient.sendEmail("lkawon@gmail.com", "test", "test", false);		
	}
	
	/**
	 * Tries to send HTML email.
	 */
	@Test
	public final void sendHtmlEmail() {
		// when
		emailClient.sendEmail("lkawon@gmail.com", "test", "<h1>test</h1><p>test</p>", true);		
	}
}
