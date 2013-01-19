package com.matzer.db.enums;

/**
 * 
 * Enumeration type defining all columns from all entities that needs to be translated.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public enum TranslationCategory {
	
	/**
	 * Email template name.
	 */
	EMAIL_TEMPLATE_NAME								(1),
	
	/**
	 * Email template description.
	 */
	EMAIL_TEMPLATE_DESCRIPTION						(2),
	
	/**
	 * Email template text.
	 */
	EMAIL_TEMPLATE_TEXT								(3),
	
	/**
	 * Email template subject.
	 */
	EMAIL_TEMPLATE_SUBJECT							(4);
	
	
	/**
	 * Identifier.
	 */
	private int id;
	
	/**
	 * Constructor.
	 * 
	 * @param id	category identifier
	 */
	private TranslationCategory(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the identifier.
	 * 
	 * @return		category identifier
	 */
	public int getId() {
		return id;
	}
}
