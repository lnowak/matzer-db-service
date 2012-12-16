package com.matzer.db.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.matzer.db.dao.IGenericDao;
import com.matzer.db.object.utils.Numbers;

/**
 * Java Persistence API implementation of abstract DAO. All DAO object should be
 * created by extending this abstract class. Used for entity manager for masterlib unit name.
 * 
 * @author lkawon@gmail.com
 * 
 * @param <T> entity
 * @param <ID> identifier type
 */
public abstract class GenericDao<T, ID extends Serializable> implements IGenericDao<T, ID> {

    /**
     * Class type to persist.
     */
    private Class<T> persistentClass;

    /**
     * Entity manager.
     */
    @PersistenceContext
    protected EntityManager entityManager = null;

      
	/**
     * Constructor.
     * 
     * @param persistentClass class type to persist
     */
    public GenericDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Returns persistent class.
     * 
  	 * @return the persistentClass
  	 */
  	protected final Class<T> getPersistentClass() {
  		return persistentClass;
  	}

	/**
	 * Returns entity manager.
	 * 
	 * @return	entity manager
	 */
	protected final EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets entity manager.
	 * 
	 * @param entityManager the entityManager to set
	 */
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* 
	 * (non-Javadoc)
	 * @see com.homersoft.wh.db.dao.IGenericDao#merge(java.lang.Object)
	 */
	@Override
	public final T merge(T entity) {
		return entityManager.merge(entity);
	}
	    
    /* (non-Javadoc)
     * @see com.homersoft.wh.db.dao.IGenericDao#save(java.lang.Object)
     */
	@Override
    public final T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    /* (non-Javadoc)
     * @see com.homersoft.wh.db.dao.IGenericDao#delete(java.lang.Object)
     */
	@Override
    public final void delete(T entity) {    	
        entityManager.remove(entity);
    }

    /* (non-Javadoc)
     * @see com.homersoft.wh.db.dao.IGenericDao#findById(java.io.Serializable)
     */
	@Override
    public final T findById(ID id) {
        return entityManager.find(persistentClass, id);
    }
    
    /* (non-Javadoc)
     * @see com.homersoft.wh.db.dao.IGenericDao#findAll()
     */
	@Override
    @SuppressWarnings("unchecked")
    public final List<T> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM ");
        sql.append(persistentClass.getName());
        sql.append(" e");
        return entityManager.createQuery(sql.toString()).getResultList();
    }
    
    /* (non-Javadoc)
     * @see com.homersoft.wh.db.dao.IGenericDao#findByTranslation(int, int, java.lang.String)
     */
	@Override
    @SuppressWarnings("unchecked")
   	public final T findByTranslation(int language, int translationCategory, String text) {
   		T entity;
   		
   		StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM ");
        sql.append(getPersistentClass().getName());
        sql.append(" e JOIN e.translations t ");
        sql.append("WHERE t.language=?1 ");
        sql.append("AND t.category=?2 ");
        sql.append("AND t.text=?3");
           
        Query query = getEntityManager().createQuery(sql.toString());
        query.setParameter(Numbers.ONE.getId(), language);
        query.setParameter(Numbers.TWO.getId(), translationCategory);
        query.setParameter(Numbers.THREE.getId(), text);
           
        try {
        	entity = (T) query.getSingleResult();
        } catch (NoResultException e) {
        	entity = null;
        }
           
        return entity;
   	}
	
	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.dao.IGenericDao#findByTranslation(int, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final T findByTranslation(int translationCategory, String text) {
		T entity;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e FROM ");
		sql.append(getPersistentClass().getName());
		sql.append(" e JOIN e.translations t ");
		sql.append("WHERE t.category=?1 ");
		sql.append("AND t.text=?2");
   
		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter(Numbers.ONE.getId(), translationCategory);
		query.setParameter(Numbers.TWO.getId(), text);
		
		try {
			entity = (T) query.getSingleResult();
		} catch (NoResultException e) {
			entity = null;
		}
   
		return entity;
	}
}
