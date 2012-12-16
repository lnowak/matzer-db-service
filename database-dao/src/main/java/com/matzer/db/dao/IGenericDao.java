package com.matzer.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface that contains all methods that will be accessible for all DAO objects.
 * 
 * @author lkawon@gmail.com
 *
 * @param <T>	entity
 * @param <ID> 	identifier type
 */
public interface IGenericDao<T, ID extends Serializable> {

	/**
	 * Merges the entity with session.
	 * 
	 * @param entity	entity to merge
	 * @return			merged entity
	 */
	T merge(T entity);
	
	/**
	 * Saves the entity.
	 * 
	 * @param entity	entity to save
	 * @return			saved entity
	 */
	T save(T entity);
	
	/**
	 * Deletes entity.
	 * 
	 * @param entity	entity to delete
	 */
	void delete(T entity);
	
	/**
	 * Finds entity by primary key.
	 * 
	 * @param id	primary key of the entity
	 * @return		entity or null
	 */
	T findById(ID id);
	
	/**
	 * Finds list of entities.
	 * 
	 * @return				list of entities
	 */
	List<T> findAll();
	
	/**
	 * Finds entity by translation text (in given language).
	 * 
	 * @param language				language id
	 * @param translationCategory	translation category
	 * @param text					text
	 * @return						entity or null
	 */
	T findByTranslation(int language, int translationCategory, String text);
	
	/**
	 * Finds entity by translation text (in any language).
	 * 
	 * @param translationCategory	translation category
	 * @param text					text
	 * @return						entity or null
	 */
	T findByTranslation(int translationCategory, String text);
}