package com.matzer.db.entity.commons;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.matzer.db.entity.LanguageTranslation;
import com.matzer.db.enums.TranslationCategory;

/**
 * 
 * Class used for translation management.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public class TranslationManager {
	
	/**
	 * Translations.
	 */
	private Collection<LanguageTranslation> translations;
	
	
	/**
	 * Public constructor.
	 * 
	 * @param translations		translation collection
	 */
	public TranslationManager(Collection<LanguageTranslation> translations) {
		this.translations = translations;
	}
	
	/**
	 * @return the translations
	 */
	public final Collection<LanguageTranslation> getTranslations() {
		return translations;
	}

	/**
	 * @param translations the translations to set
	 */
	public final void setTranslations(Collection<LanguageTranslation> translations) {
		this.translations = translations;
	}

	/**
	 * Searches for the translation and returns the translated text or default value.
	 * 
	 * @param language		identifier of the language (from dictionary)
	 * @param category		translation category
	 * @param defaultText	default text
	 * @return				found or default text
	 */
	public final String getText(int language, TranslationCategory category, String defaultText) {
		String text = defaultText;		
		for (LanguageTranslation translation : translations) {
			if (translation.getLanguage() == language && translation.getCategory() == category.getId()) {
				text = translation.getText();
				break;
			}
		}
		
		return text;
	}
	
	/**
	 * Gets all translations for given category.
	 * 
	 * @param category		translation category
	 * @return				list of translations
	 */
	public final Set<LanguageTranslation> getByCategory(TranslationCategory category) {
		Set<LanguageTranslation> result = new HashSet<LanguageTranslation>();
		for (LanguageTranslation tranlation : translations) {
			if (tranlation.getCategory() == category.getId()) {
				result.add(tranlation);
			}
		}
				
		return result;
	}
	
	/**
	 * Searches for the translation and returns the translated text.
	 * 
	 * @param language		identifier of the language (from dictionary)
	 * @param category		translation category
	 * @return				found or null
	 */
	public final String getText(int language, TranslationCategory category) {			
		return getText(language, category, null);
	}
	
	/**
	 * Saves the new translation for given language and category.
	 * 
	 * @param language		identifier of the language (from dictionary)
	 * @param category		translation category
	 * @param text			text of the translation
	 */
	public final void saveOrUpdateText(int language, TranslationCategory category, String text) {
		boolean updated = false;
		for (LanguageTranslation tranlation : translations) {
			if (tranlation.getLanguage() == language && tranlation.getCategory() == category.getId()) {
				tranlation.setText(text);
				updated = true;
				break;
			}
		}
		
		if (!updated) {						
			translations.add(new LanguageTranslation(language, category.getId(), text));
		}
	}
}
