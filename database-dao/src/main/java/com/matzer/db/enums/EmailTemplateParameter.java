package com.matzer.db.enums;

/**
 * 
 * Contains all available email template parameters.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public enum EmailTemplateParameter {

	/**
	 * Activation code.
	 */
	ACTIVATION_CODE			("\\$\\{activation_code\\}"),
	
	/**
	 * Reset code.
	 */
	RESET_CODE				("\\$\\{reset_code\\}"),
	
	/**
	 * Account email.
	 */
	EMAIL 					("\\$\\{email\\}"),
	
	/**
	 * Account password.
	 */
	PASSWORD				("\\$\\{password\\}");
	
	
	/**
	 * Parameter name.
	 */
	private String name;
	
	
	/** 
	 * Constructor.
	 * 
	 * @param name		parameter name
	 */
	private EmailTemplateParameter(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
}
