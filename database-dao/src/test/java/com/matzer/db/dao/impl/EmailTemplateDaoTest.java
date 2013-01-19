package com.matzer.db.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.matzer.db.commons.dbunit.AbstractDbUnitTests;
import com.matzer.db.dao.IEmailTemplateDao;
import com.matzer.db.entity.EmailTemplate;
import com.matzer.db.enums.EmailTemplateType;

/**
 * 
 * Used for testing the {@link EmailTemplateDao}.
 * 
 * @author lkawon@gmail.com
 *
 */
@ContextConfiguration(locations = { "classpath:spring-config-hsqldb.xml" })
@TransactionConfiguration(defaultRollback = false)
public class EmailTemplateDaoTest extends AbstractDbUnitTests {	
	
	/**
	 * DAO for email template.
	 */
	@Autowired
	private IEmailTemplateDao emailTemplateDao;
	
	
	/**
	 * Adds new template.
	 */
	@Test
	public void addNewTemplate() {
		// given
		EmailTemplate template = new EmailTemplate();
		template.setName(0, "nazwa");
		template.setName(1, "name");
		template.setDescription(0, "opis");
		template.setDescription(1, "description");
		template.setSubject(0, "temat");
		template.setSubject(1, "subject");
		template.setTemplateId(EmailTemplateType.ACTIVATION.getId());
		template.setText(0, "tekst");
		template.setText(1, "text");
		
		// when
		emailTemplateDao.save(template);
		
		// then
		Assert.assertTrue(template.getId() > 0);
	}
	
	/**
	 * Gets templates from DB.
	 */
	@Test(dependsOnMethods = "addNewTemplate")
	public void getEmailTemplate() {
		// when
		EmailTemplate acivationTemplate = emailTemplateDao.findByTemplateId(EmailTemplateType.ACTIVATION.getId());
		EmailTemplate passwordResetTemplate = emailTemplateDao.findByTemplateId(EmailTemplateType.PASSWORD_RESET.getId());
		
		// then
		Assert.assertNotNull(acivationTemplate);
		Assert.assertNull(passwordResetTemplate);
	}
	
	/**
	 * Deletes templates from DB.
	 */
	@Test(dependsOnMethods = "getEmailTemplate")
	public void deleteEmailTemplate() {
		// given
		EmailTemplate acivationTemplate = emailTemplateDao.findByTemplateId(EmailTemplateType.ACTIVATION.getId());
		
		// when
		emailTemplateDao.delete(acivationTemplate);
		
		// then
		Assert.assertNull(emailTemplateDao.findByTemplateId(EmailTemplateType.ACTIVATION.getId()));
	}
}
