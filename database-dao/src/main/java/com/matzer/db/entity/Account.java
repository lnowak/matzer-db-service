package com.matzer.db.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 
 * Class representing system account. 
 * 
 * @author lkawon@gmail.com
 *
 */
@Entity
@Table(name = "ACCOUNT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Account implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -13712461764812132L;

	/**
	 * Primary key.
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	/**
	 * Type of the account.
	 */
	@Column(name = "TYPE")
	private int type;
	
	/**
	 * First name of the account.
	 */
	@Column(name = "FIRST_NAME", length = 100)
	private String firstName;
	
	/**
	 * Last name of the account.
	 */
	@Column(name = "LAST_NAME", length = 100)
	private String lastName;
	
	/**
	 * Gender of the account.
	 */
	@Column(name = "GENDER")
	private Character gender;
	
	/**
	 * Province.
	 */
	@Column(name = "PROVINCE", length = 100)
	private String province;
	
	/**
	 * City.
	 */
	@Column(name = "CITY", length = 100)
	private String city;
	
	/**
	 * Birth date.
	 */
	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	
	/**
	 * Email.
	 */
	@Column(name = "EMAIL", length = 100)
	private String email;
	
	/**
	 * Password.
	 */
	@Column(name = "PASSWORD", length = 50)
	private String password;
	
	/**
	 * Facebook identifier.
	 */
	@Column(name = "FACEBOOK_ID")
	private Long facebookId;
	
	/**
	 * Creation date.
	 */
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	/**
	 * Modification date.
	 */
	@Column(name = "MODIFICATION_DATE")
	private Date modificationDate;
	
	
	/**
	 * Default constructor.
	 */
	public Account() {
		
	}

	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public final int getType() {
		return type;
	}

	public final void setType(int type) {
		this.type = type;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final Character getGender() {
		return gender;
	}

	public final void setGender(Character gender) {
		this.gender = gender;
	}

	public final String getProvince() {
		return province;
	}

	public final void setProvince(String province) {
		this.province = province;
	}

	public final String getCity() {
		return city;
	}

	public final void setCity(String city) {
		this.city = city;
	}

	public final Date getBirthDate() {
		return birthDate;
	}

	public final void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final Long getFacebookId() {
		return facebookId;
	}

	public final void setFacebookId(Long facebookId) {
		this.facebookId = facebookId;
	}

	public final Date getCreationDate() {
		return creationDate;
	}

	public final void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public final Date getModificationDate() {
		return modificationDate;
	}

	public final void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
}
