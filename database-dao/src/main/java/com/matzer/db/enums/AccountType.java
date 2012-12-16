package com.matzer.db.enums;

/**
 * 
 * Contains all possible types of accounts.
 * 
 * @author lkawon@gmail.com
 *
 */
public enum AccountType {

	/**
	 * Administrator of the service.
	 */
	ADMIN		(AccountTypeHelper.ADMIN, 	1),
	
	/**
	 * Typical user of the service.
	 */
	USER		(AccountTypeHelper.USER,	2);
	
	
	/**
	 * Name of the account type.
	 */
	private String name;
	
	/**
	 * Identifier of the account type.
	 */
	private int id;
	
	
	/**
	 * Constructor.
	 * 
	 * @param name		name of the type
	 * @param id		identifier of the type
	 */
	private AccountType(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Finds account type by name.
	 * 
	 * @param name		name of the account type
	 * @return			account type or null
	 */
	public static final AccountType find(String name) {
		AccountType accountType = null;
		for (AccountType temp : values()) {
			if (temp.getName().equals(name)) {
				accountType = temp;
				break;
			}
		}
		
		return accountType;
	}
	
	/**
	 * Finds account type by identifier.
	 * 
	 * @param id		identifier of the account type
	 * @return			account type or null
	 */
	public static final AccountType find(int id) {
		AccountType accountType = null;
		for (AccountType temp : values()) {
			if (temp.getId() == id) {
				accountType = temp;
				break;
			}
		}
		
		return accountType;
	}
}
