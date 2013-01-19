package com.matzer.db.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.service.IEmailService;
import com.matzer.db.api.service.exception.ApiException;
import com.matzer.db.commons.mail.ISmtpClient;
import com.matzer.db.dao.IEmailTemplateDao;
import com.matzer.db.entity.Account;
import com.matzer.db.entity.EmailTemplate;
import com.matzer.db.enums.EmailTemplateParameter;
import com.matzer.db.enums.EmailTemplateType;

/**
 * 
 * Service that allows to send email notifications.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
@Component
public class EmailService implements IEmailService {

	/**
	 * Log4j logger.
	 */
	private final static Logger LOG = Logger.getLogger(EmailService.class);
	
	/**
	 * SMTP client.
	 */
	@Autowired
	private ISmtpClient smtpClient;
	
	/**
	 * Email template DAO.
	 */
	@Autowired
	private IEmailTemplateDao emailTemplateDao;
	
	
	/**
	 * @param smtpClient the smtpClient to set
	 */
	public final void setSmtpClient(ISmtpClient smtpClient) {
		this.smtpClient = smtpClient;
	}
	
	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.masterlib.service.IEmailService#sendEmal(
	 * com.homersoft.wh.db.dictionary.masterlib.EmailTemplateType, com.homersoft.wh.db.entity.masterlib.MasterLibAccount)
	 */
	@Override
	public final void sendEmail(EmailTemplateType type, Account account) {
		if (type != null && account != null) {					
			sendEmail(type, account.getLanguage(), account.getEmail(), prepareParameters(account));
		} else {
			throw new ApiException(ErrorCode.MISSING_PARAMETER);
		}
	}
	
	/**
	 * Prepares map with template parameters.
	 * 
	 * @param account		account
	 * @return				map with parameters
	 */
	private Map<EmailTemplateParameter, String> prepareParameters(Account account) {
		Map<EmailTemplateParameter, String> parameters = new HashMap<EmailTemplateParameter, String>();
		parameters.put(EmailTemplateParameter.ACTIVATION_CODE, account.getActivationCode());
		parameters.put(EmailTemplateParameter.RESET_CODE, account.getResetCode());
		
		return parameters;
	}
	
	/**
	 * Fills the template text with parameters.
	 * 
	 * @param text			text of the template
	 * @param parameters	template parameters
	 * @return				filled template text
	 */
	private String fillTemplateText(String text, Map<EmailTemplateParameter, String> parameters) {
		for (EmailTemplateParameter param : EmailTemplateParameter.values()) {
			if (param != null) {
				text = text.replaceAll(param.getName(), parameters.get(param));
			}
		}
		
		return text;
	}
	
	/**
	 * Sends email notification from template.
	 * 
	 * @param emailTemplateType		email template type\
	 * @param language				email language
	 * @param email					destination email 
	 * @param parameters			template parameters
	 */
	private void sendEmail(EmailTemplateType emailTemplateType, int language, String email, 
			Map<EmailTemplateParameter, String> parameters) {
		EmailTemplate emailTemplate = emailTemplateDao.findByTemplateId(emailTemplateType.getId());
		if (emailTemplate != null) {
			String subject = emailTemplate.getSubject(language);
			String text = emailTemplate.getText(language);
						
			smtpClient.sendEmail(email, subject, fillTemplateText(text, parameters), emailTemplate.isHtml());
		} else {
			LOG.error("Template " + emailTemplateType + " was not found in database.");
			throw new ApiException(ErrorCode.EMAIL_TEMPLATE_WAS_NOT_FOUND);			
		}
	}
}
