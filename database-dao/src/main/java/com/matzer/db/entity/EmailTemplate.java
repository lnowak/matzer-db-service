package com.matzer.db.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.matzer.db.entity.commons.TranslationBase;
import com.matzer.db.enums.TranslationCategory;

/**
 * 
 * Entity for email templates.
 * 
 * @author lkawon@gmail.com
 *
 */
@Entity
@Table(name = "EMAIL_TEMPLATE")
public class EmailTemplate extends TranslationBase implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -2549848785301327148L;

	/**
	 * Primary key.
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	/**
	 * Template id.
	 */
	@Column(name = "TEMPLATE_ID")
	private int templateId;	
	
	/**
	 * Name of the template.
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * Description of the template.
	 */
	@Column(name = "DESCRIPTION")
	private String description;
	
	/**
	 * Email subject;
	 */
	@Column(name = "SUBJECT")
	private String subject;
	
	/**
	 * Text of the template.
	 */
	@Column(name = "TEXT")
	private String text;
	
	/**
	 * Indicates that the mail includes HTML tags.
	 */
	@Column(name = "IS_HTML")
	private boolean isHtml; 
	
	/**
	 * Language translations.
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMAIL_TEMPLATE_ID", nullable = false)
	private Set<LanguageTranslation> translations = new HashSet<LanguageTranslation>();
	
	
	/**
	 * Default constructor.
	 */
	public EmailTemplate() {
		registerTransaltions(translations);
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
	 * @return the tempalteId
	 */
	public final int getTemplateId() {
		return templateId;
	}

	/**
	 * @param tempalteId the tempalteId to set
	 */
	public final void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	
	/**
	 * Get translation for name.
	 * 
	 * @param language
	 * @return the name
	 */
	public final String getName(int language) {
		return getTranslation(language, TranslationCategory.EMAIL_TEMPLATE_NAME, name);
	}

	/**
	 * Set translation for name.
	 * 
	 * @param language
	 * @param name the name to set
	 */
	public final void setName(int language, String name) {
		this.name = setTranslation(language, TranslationCategory.EMAIL_TEMPLATE_NAME, name, this.name);
	}

	/**
	 * Get all translations for names.
	 * 
	 * @return		collection of translation
	 */
	public final Set<LanguageTranslation> getNames() {
		return getTranslations(TranslationCategory.EMAIL_TEMPLATE_NAME, name);
	}
	
	/**
	 * Sets translations for names.
	 * 
	 * @param translations	collection of translations
	 */
	public final void setNames(Set<LanguageTranslation> translations) {
		if (translations != null) {
			for (LanguageTranslation translation : translations) {
				translation.setCategory(TranslationCategory.EMAIL_TEMPLATE_NAME.getId());
				if (translation.getLanguage() == DEFAULT_LANGUAGE_ID) {
					name = translation.getText();
				} else {
					this.translations.add(translation);
				}
			}				
		}
	}
	
	/**
	 * Get translation for description.
	 * 
	 * @param language		language id
	 * @return				description
	 */
	public final String getDescription(int language) {
		return getTranslation(language, TranslationCategory.EMAIL_TEMPLATE_DESCRIPTION, description);
	}

	/**
	 * Set translation for description.
	 * 
	 * @param language		language id
	 * @param description	description
	 */
	public final void setDescription(int language, String description) {
		this.description = setTranslation(language, TranslationCategory.EMAIL_TEMPLATE_DESCRIPTION, 
				description, this.description);
	}

	/**
	 * Get all translations for descriptions.
	 * 
	 * @return		collection of translation
	 */
	public final Set<LanguageTranslation> getDescriptions() {
		return getTranslations(TranslationCategory.EMAIL_TEMPLATE_DESCRIPTION, description);
	}
	
	/**
	 * Sets translations for descriptions.
	 * 
	 * @param translations	collection of translations
	 */
	public final void setDescriptions(Set<LanguageTranslation> translations) {
		if (translations != null) {
			for (LanguageTranslation translation : translations) {
				translation.setCategory(TranslationCategory.EMAIL_TEMPLATE_DESCRIPTION.getId());
				if (translation.getLanguage() == DEFAULT_LANGUAGE_ID) {
					description = translation.getText();
				} else {
					this.translations.add(translation);
				}
			}				
		}
	}
	
	/**
	 * Get translation for text.
	 * 
	 * @param language		language id
	 * @return				description
	 */
	public final String getText(int language) {
		return getTranslation(language, TranslationCategory.EMAIL_TEMPLATE_TEXT, text);
	}

	/**
	 * Set translation for text.
	 * 
	 * @param language		language id
	 * @param description	description
	 */
	public final void setText(int language, String text) {
		this.text = setTranslation(language, TranslationCategory.EMAIL_TEMPLATE_TEXT, 
				text, this.text);
	}

	/**
	 * Get all translations for texts.
	 * 
	 * @return		collection of translation
	 */
	public final Set<LanguageTranslation> getTexts() {
		return getTranslations(TranslationCategory.EMAIL_TEMPLATE_TEXT, text);
	}
	
	/**
	 * Sets translations for texts.
	 * 
	 * @param translations	collection of translations
	 */
	public final void setTexts(Set<LanguageTranslation> translations) {
		if (translations != null) {
			for (LanguageTranslation translation : translations) {
				translation.setCategory(TranslationCategory.EMAIL_TEMPLATE_TEXT.getId());
				if (translation.getLanguage() == DEFAULT_LANGUAGE_ID) {
					text = translation.getText();
				} else {
					this.translations.add(translation);
				}
			}				
		}
	}

	/**
	 * Get translation for subject.
	 * 
	 * @param language		language id
	 * @return				subject
	 */
	public final String getSubject(int language) {
		return getTranslation(language, TranslationCategory.EMAIL_TEMPLATE_SUBJECT, subject);
	}

	/**
	 * Set translation for subject.
	 * 
	 * @param language		language id
	 * @param subject		subject
	 */
	public final void setSubject(int language, String subject) {
		this.subject = setTranslation(language, TranslationCategory.EMAIL_TEMPLATE_SUBJECT, 
				subject, this.subject);
	}

	/**
	 * Get all translations for subjects.
	 * 
	 * @return		collection of translation
	 */
	public final Set<LanguageTranslation> getSubjects() {
		return getTranslations(TranslationCategory.EMAIL_TEMPLATE_SUBJECT, subject);
	}
	
	/**
	 * Sets translations for texts.
	 * 
	 * @param translations	collection of translations
	 */
	public final void setSubjects(Set<LanguageTranslation> translations) {
		if (translations != null) {
			for (LanguageTranslation translation : translations) {
				translation.setCategory(TranslationCategory.EMAIL_TEMPLATE_SUBJECT.getId());
				if (translation.getLanguage() == DEFAULT_LANGUAGE_ID) {
					subject = translation.getText();
				} else {
					this.translations.add(translation);
				}
			}				
		}
	}
	
	/**
	 * @return the isHtml
	 */
	public final boolean isHtml() {
		return isHtml;
	}

	/**
	 * @param isHtml the isHtml to set
	 */
	public final void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}

	/**
	 * @return the translations
	 */
	public final Set<LanguageTranslation> getTranslations() {
		return translations;
	}

	/**
	 * @param translations the translations to set
	 */
	public final void setTranslations(Set<LanguageTranslation> translations) {
		this.translations = translations;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isHtml ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + templateId;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((translations == null) ? 0 : translations.hashCode());
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
		EmailTemplate other = (EmailTemplate) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (isHtml != other.isHtml)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (templateId != other.templateId)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (translations == null) {
			if (other.translations != null)
				return false;
		} else if (!translations.equals(other.translations))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailTemplate [id=");
		builder.append(id);
		builder.append(", templateId=");
		builder.append(templateId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", text=");
		builder.append(text);
		builder.append(", isHtml=");
		builder.append(isHtml);
		builder.append(", translations=");
		builder.append(translations);
		builder.append("]");
		return builder.toString();
	}
}
