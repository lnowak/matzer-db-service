package com.matzer.db.api.dto.object;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * DTO object for account entity.
 * 
 * @author lkawon@gmail.com
 *
 */
@XmlRootElement(name = "account")
@XmlType(propOrder = { "id", "type", "firstName", "lastName", "gender", "province", "city", "birthDate", "email", "password", 
		"language", "facebookId", "creationDate", "modificationDate", "isActive", "activationCode", "resetCode"})
public class AccountDto {

	/**
	 * Primary key.
	 */
	private Long id;
	
	/**
	 * Type of the account.
	 */
	private Integer type;
	
	/**
	 * First name of the account.
	 */
	private String firstName;
	
	/**
	 * Last name of the account.
	 */
	private String lastName;
	
	/**
	 * Gender of the account.
	 */
	private Character gender;
	
	/**
	 * Province.
	 */
	private String province;
	
	/**
	 * City.
	 */
	private String city;
	
	/**
	 * Birth date.
	 */
	private Date birthDate;
	
	/**
	 * Email.
	 */
	private String email;
	
	/**
	 * Password.
	 */
	private String password;
	
	/**
	 * Language identifier.
	 */
	private Integer language;
	
	/**
	 * Facebook identifier.
	 */
	private Long facebookId;
	
	/**
	 * Creation date.
	 */
	private Date creationDate;
	
	/**
	 * Modification date.
	 */
	private Date modificationDate;
	
	/**
	 * Indicates that the account is active.
	 */
	private Boolean isActive;
	
	/**
	 * Activation code.
	 */
	private String activationCode;
	
	/**
	 * Reset code.
	 */
	private String resetCode;
	
	
	/**
	 * Default constructor.
	 */
	public AccountDto() {
		
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Integer getType() {
		return type;
	}

	public final void setType(Integer type) {
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

	public final Integer getLanguage() {
		return language;
	}

	public final void setLanguage(Integer language) {
		this.language = language;
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

	public final Boolean getIsActive() {
		return isActive;
	}

	public final void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public final String getActivationCode() {
		return activationCode;
	}

	public final void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public final String getResetCode() {
		return resetCode;
	}

	public final void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activationCode == null) ? 0 : activationCode.hashCode());
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((facebookId == null) ? 0 : facebookId.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime
				* result
				+ ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
		result = prime * result
				+ ((resetCode == null) ? 0 : resetCode.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDto other = (AccountDto) obj;
		if (activationCode == null) {
			if (other.activationCode != null)
				return false;
		} else if (!activationCode.equals(other.activationCode))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (facebookId == null) {
			if (other.facebookId != null)
				return false;
		} else if (!facebookId.equals(other.facebookId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (resetCode == null) {
			if (other.resetCode != null)
				return false;
		} else if (!resetCode.equals(other.resetCode))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountDto [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", language=");
		builder.append(language);
		builder.append(", facebookId=");
		builder.append(facebookId);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", modificationDate=");
		builder.append(modificationDate);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", activationCode=");
		builder.append(activationCode);
		builder.append(", resetCode=");
		builder.append(resetCode);
		builder.append("]");
		return builder.toString();
	}
}
