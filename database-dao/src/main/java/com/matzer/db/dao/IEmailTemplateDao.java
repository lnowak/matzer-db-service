package com.matzer.db.dao;

import com.matzer.db.entity.EmailTemplate;

/**
 * 
 * DAO interface for email templates.
 * 
 * @author lkawon@gmail.com
 *
 */
public interface IEmailTemplateDao extends IGenericDao<EmailTemplate, Long> {

	/**
	 * Finds email template by its identifier.
	 * 
	 * @param templateId		template identifier
	 * @return					email template
	 */
	EmailTemplate findByTemplateId(int templateId);
}