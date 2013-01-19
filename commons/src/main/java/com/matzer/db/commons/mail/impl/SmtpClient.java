package com.matzer.db.commons.mail.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.commons.mail.ISmtpClient;

/**
 * 
 * Implementation of the email client.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
@Component
public class SmtpClient implements ISmtpClient {

	/**
	 * Log4j logger.
	 */
	private static final Logger LOG = Logger.getLogger(SmtpClient.class);
	
	/**
	 * SMTP host.
	 */
	private String smtpHost;
	
	/**
	 * SMTP port.
	 */
	private int smtpPort;
	
	/**
	 * Username.
	 */
	private String username;
	
	/**
	 * Password.
	 */
	private String password;
	
	/**
	 * Email adress from.
	 */
	private String from;
	
	/**
	 * Java mail authenticator.
	 */
	private Authenticator authenticator;
	
	
	public SmtpClient() {
		
	}

	/**
	 * @return the smtpHost
	 */
	public final String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * @param smtpHost the smtpHost to set
	 */
	public final void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * @return the smtpPort
	 */
	public final int getSmtpPort() {
		return smtpPort;
	}

	/**
	 * @param smtpPort the smtpPort to set
	 */
	public final void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * @return the username
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public final void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the from
	 */
	public final String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public final void setFrom(String from) {
		this.from = from;
	}


	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.mail.ISmtpClient#sendEmail(java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public final void sendEmail(String recipients, String subject, String text, boolean isHtml) {
		StringBuilder log = new StringBuilder();
		log.append("Sending email [");
		log.append("subject=");
		log.append(subject);
		log.append(", text=");
		log.append(text);
		log.append(", isHtml=");
		log.append(isHtml);
		log.append("] to ");
		log.append(recipients);
		log.append(".");
		
		LOG.info(log);
		
		if (smtpHost != null && smtpPort != 0) {
			if (authenticator == null) {
				if (username != null && password != null) {
					authenticator = new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					};
				} else {
					throw new EmailException(ErrorCode.MISSING_EMAIL_CLIENT_CREDENTIALS);
				}
			}
			
			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtpHost);
			properties.put("mail.smtp.socketFactory.port", smtpPort);
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", smtpPort);
			
			Session session = Session.getInstance(properties, authenticator);
			Message message = new MimeMessage(session);
			
			if (from != null) {
				try {
					message.setFrom(new InternetAddress(from));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
					message.setSubject(subject);
					if (isHtml) {
						message.setContent(text, "text/html;charset=utf-8");
					} else {
						message.setText(text);
					}
		 
					Transport.send(message);					
				} catch (AddressException e) {
					throw new EmailException(ErrorCode.INCORRECT_EMAIL_ADDRESS, e);
				} catch (MessagingException e) {
					throw new EmailException(ErrorCode.EMAIL_CLIENT_UNKNOWN_ERROR, e);
				}
			} else {
				throw new EmailException(ErrorCode.MISSING_EMAIL_CLIENT_CONFIGURATION);
			}
		} else {
			throw new EmailException(ErrorCode.MISSING_EMAIL_CLIENT_CONFIGURATION);
		}
	}

}
