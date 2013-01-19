package com.matzer.db.enums;

/**
 * 
 * Describes all available email templates.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public enum EmailTemplateType {

	/**
	 * Account activation.
	 */
	ACTIVATION			(1),
	
	/**
	 * Password reset.
	 */
	PASSWORD_RESET		(2),
	
	/**
	 * Account removal
	 */
	ACCOUNT_REMOVAL		(3);
	
	
	/**
	 * Email template identifier.
	 */
	private int id;
	
	
	/**
	 * Constructor.
	 * 
	 * @param id	template identifier
	 */
	private EmailTemplateType(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}
}
