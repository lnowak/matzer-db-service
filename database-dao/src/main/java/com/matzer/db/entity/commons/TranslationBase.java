package com.matzer.db.entity.commons;

import java.util.Set;

import com.matzer.db.entity.LanguageTranslation;
import com.matzer.db.enums.TranslationCategory;

/**
 * Class used as base for translation operations.
 */
public abstract class TranslationBase {		
	
	/**
	 * Default identifier for language identifier.
	 */
	protected static final int DEFAULT_LANGUAGE_ID = 0;
	
	/**
	 * Handles translation operation.
	 */
	private TranslationManager translationManager = null;
	
	
	/**
	 * Registers the actual collection of translations.
	 * 
	 * @param translations	collection with translations
	 */
	public final void registerTransaltions(Set<LanguageTranslation> translations) {
		if (translationManager == null || translationManager.getTranslations() != translations) {
			translationManager = new TranslationManager(translations);		
		}
	}
	
	/**
	 * Get translation. 
	 * 
	 * @param language			language identifier
	 * @param defaultValue		default value
	 * @return					translated value
	 */
	public final String getTranslation(int language, TranslationCategory translationCategory, 
			String defaultValue) {
		return translationManager.getText(language, translationCategory, defaultValue);
	}
	
	/**
	 * Gets all translation for given category.
	 * 
	 * @param translationCategory
	 * @return
	 */
	public final Set<LanguageTranslation> getTranslations(TranslationCategory translationCategory, String defaultValue) {
		Set<LanguageTranslation> translations = translationManager.getByCategory(translationCategory);
		if (defaultValue != null) {						
			translations.add(new LanguageTranslation(DEFAULT_LANGUAGE_ID, translationCategory.getId(), defaultValue));			
		}
		
		return translations;
	}
	
	/**
	 * Set translation.
	 * 
	 * @param language				language identifier
	 * @param transaltionCategory	translation category
	 * @param value					value to set
	 * @param defaultValue			default value to set if language not found
	 * @return						default value
	 */
	public final String setTranslation(int language, TranslationCategory transaltionCategory, String value, 
			String defaultValue) {
		String tempValue = defaultValue;
		if (language != DEFAULT_LANGUAGE_ID) {
			translationManager.saveOrUpdateText(language, transaltionCategory, value);
		} else {
			tempValue = value;
		}					
		
		return tempValue;
	}
}
