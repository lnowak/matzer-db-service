package com.matzer.db.commons.utils;

/**
 * 
 * Enumeration type defining all columns from all entities that needs to be translated.
 * 
 * @author lkawon@gmail.com
 *
 */
public enum Numbers {

	/**
	 * Magic number 1.
	 */
	ONE			(1),
	
	/**
	 * Magic number 2.
	 */
	TWO			(2),
	
	/**
	 * Magic number 3.
	 */
	THREE		(3),
	
	/**
	 * Magic number 4.
	 */
	FOUR		(4),
	
	/**
	 * Magic number 5.
	 */
	FIVE		(5),
	
	/**
	 * Magic number 6.
	 */
	SIX			(6),
	
	/**
	 * Magic number 7.
	 */
	SEVEN		(7),
	
	/**
	 * Magic number 8.
	 */
	EIGHT		(8),
	
	/**
	 * Magic number 9.
	 */
	NINE		(9),
	
	/**
	 * Magic number 10.
	 */
	TEN			(10),
	
	/**
	 * Magic number 11.
	 */
	ELEVEN			(11);
	
	
	/**
	 * Identifier.
	 */
	private int id;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	private Numbers(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the identifier.
	 * 
	 * @return	number identifier
	 */
	public int getId() {
		return id;
	}
}
