package com.matzer.db.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * 
 * Class for generating the hash from strings.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public final class HashUtils {

	/**
	 * Log4j logger.
	 */
	private static final Logger LOG = Logger.getLogger(HashUtils.class);
	
	/**
	 * 
	 * Types of supported HASH algorithms.
	 * 
	 * @author lukasz.nowak@homersoft.com
	 *
	 */
	public enum HashType {
		
		/**
		 * SHA hashing.
		 */
		SHA		("SHA");
		
		
		/**
		 * Name of the hash type.
		 */
		private String name;
		
		
		/**
		 * Constructor.
		 * 
		 * @param name	name of the hash
		 */
		private HashType(String name) {
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

	
	/**
	 * Hidden constructor.
	 */
	private HashUtils() {
		
	}
	
	/**
	 * Generates SHA-256 hash from string.
	 * 
	 * @param text		text string
	 * @return			hash
	 */
	public static final String generateHash(HashType type, String text) {
		String hashString = null;
		if (text != null) {
	        MessageDigest md = null;
			try {
				md = MessageDigest.getInstance(type.getName());
			} catch (NoSuchAlgorithmException e) {
				LOG.error("Exception occured.", e);
			}
	        md.update(text.getBytes());
	        byte byteData[] = md.digest();
	 
	        StringBuffer hash = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	        	hash.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        hashString = hash.toString();
		}
		
		return hashString;
	}
}
