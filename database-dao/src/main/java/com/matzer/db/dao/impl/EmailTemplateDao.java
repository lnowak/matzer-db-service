package com.matzer.db.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.matzer.db.dao.IEmailTemplateDao;
import com.matzer.db.entity.EmailTemplate;

/**
 * 
 * DAO for email templates.
 * 
 * @author lkawon@gmail.com
 *
 */
@Repository
public class EmailTemplateDao extends GenericDao<EmailTemplate, Long> implements IEmailTemplateDao {

	/**
	 * Default constructor.
	 */
	public EmailTemplateDao() {
		super(EmailTemplate.class);
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.dao.IEmailTemplateDao#findByTemplateId(int)
	 */
	@Override
	public final EmailTemplate findByTemplateId(int templateId) {
		EmailTemplate emailTemplate;
		
		StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM ");
        sql.append(getPersistentClass().getName());
        sql.append(" e ");
        sql.append("WHERE e.templateId=?1");
        
        Query query = getEntityManager().createQuery(sql.toString());
        query.setHint("org.hibernate.cacheable", true);
        query.setParameter(1, templateId);
        
        try {
        	emailTemplate = (EmailTemplate) query.getSingleResult();
        } catch (NoResultException e) {
        	emailTemplate = null;
        }
        
        return emailTemplate;
	}
}
