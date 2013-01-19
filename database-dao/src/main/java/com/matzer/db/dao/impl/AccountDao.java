package com.matzer.db.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.matzer.db.dao.IAccountDao;
import com.matzer.db.entity.Account;

/**
 * 
 * DAO object for {@link Account} class.
 * 
 * @author lkawon@gmail.com
 *
 */
@Repository
public class AccountDao extends GenericDao<Account, Long> implements IAccountDao {

	/**
	 * Default constructor.
	 */
	public AccountDao() {
		super(Account.class);
	}
	

	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.dao.masterlib.IMasterLibAccountDao#findByEmail(java.lang.String)
	 */
	@Override
	public final Account findByEmail(String email) {
		Account account;
		
		StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM ");
        sql.append(getPersistentClass().getName());
        sql.append(" e ");
        sql.append("WHERE e.email=?1");
        
        Query query = getEntityManager().createQuery(sql.toString());
        query.setHint("org.hibernate.cacheable", true);
        query.setParameter(1, email);
        
        try {
        	account = (Account) query.getSingleResult();
        } catch (NoResultException e) {
        	account = null;
        }
        
        return account;
	}	

	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.dao.masterlib.IAccountDao#findByActivationCode(java.lang.String)
	 */
	@Override
	public final Account findByActivationCode(String activationCode) {
		Account account;
		
		StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM ");
        sql.append(getPersistentClass().getName());
        sql.append(" e ");
        sql.append("WHERE e.activationCode=?1");
        
        Query query = getEntityManager().createQuery(sql.toString());
        query.setHint("org.hibernate.cacheable", true);
        query.setParameter(1, activationCode);
        
        try {
        	account = (Account) query.getSingleResult();
        } catch (NoResultException e) {
        	account = null;
        }
        
        return account;
	}


	/* (non-Javadoc)
	 * @see com.matzer.db.dao.IAccountDao#findByResetCode(java.lang.String)
	 */
	@Override
	public final Account findByResetCode(String resetCode) {
		Account account;
		
		StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM ");
        sql.append(getPersistentClass().getName());
        sql.append(" e ");
        sql.append("WHERE e.resetCode=?1");
        
        Query query = getEntityManager().createQuery(sql.toString());
        query.setHint("org.hibernate.cacheable", true);
        query.setParameter(1, resetCode);
        
        try {
        	account = (Account) query.getSingleResult();
        } catch (NoResultException e) {
        	account = null;
        }
        
        return account;
	}
}
