package com.matzer.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Class represents entity responsible for storing all information about language transaltions.
 * 
 * @author lkawon@gmail.com
 *
 */
@Entity
@Table(name = "LANGUAGE_TRANSLATION")
public class LanguageTranslation implements Serializable {
	
	/**
	 * Automatically generated.
	 */
	private static final long serialVersionUID = 3540807864254618981L;
	
	/**
	 * Primary key.
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	/**
	 * Used language.
	 */
	@Column(name = "LANGUAGE")
	private int language;
	
	/**
	 * Subject category.
	 */
	@Column(name = "CATEGORY")
	private int category;
	
	/**
	 * Text of the translation.
	 */
	@Column(name = "TEXT")
	private String text;
		
	
	/**
	 * Default constructor.
	 */
	public LanguageTranslation() {
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param language		language identifier
	 * @param category		category identifier
	 * @param text			text of the translation
	 */
	public LanguageTranslation(int language, int category, String text) {
		this.language = language;
		this.category = category;
		this.text = text;
	}
	
	/**
	 * @return the id
	 */
	public final long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the language
	 */
	public final int getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public final void setLanguage(int language) {
		this.language = language;
	}

	/**
	 * @return the category
	 */
	public final int getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public final void setCategory(int category) {
		this.category = category;
	}

	/**
	 * @return the text
	 */
	public final String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public final void setText(String text) {
		this.text = text;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + category;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + language;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LanguageTranslation other = (LanguageTranslation) obj;
		if (category != other.category) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (language != other.language) {
			return false;
		}
		if (text == null) {
			if (other.text != null) {
				return false;
			}
		} else if (!text.equals(other.text)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LanguageTranslation [id=");
		builder.append(id);
		builder.append(", language=");
		builder.append(language);
		builder.append(", category=");
		builder.append(category);
		builder.append(", text=");
		builder.append(text);
		builder.append("]");
		return builder.toString();
	}
}
