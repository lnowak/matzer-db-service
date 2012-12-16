package com.matzer.db.api.security;

import java.util.Random;

/**
 * 
 * Helper class that is generating token strings.
 * 
 * @author lkawon@gmail.com
 *
 */
public class RandomCodeGenerator {

	/**
	 * Token length.
	 */
	private static final int TOKEN_LENGTH = 16; 
	
	/**
	 * Allowed characters in tokens.
	 */
	private static final String[] ALOWED_CHARACTERS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
							"a", "b", "c", "d", "e", "f" };
	
	/**
	 * Random generator.
	 */
	private static final Random RANDOM = new Random();
	
	
	/**
	 * Hidden constructor.
	 */
	private RandomCodeGenerator() {
		
	}
	
	/**
	 * Generates random token.
	 * 
	 * @return	string token
	 */
	public static final String getenerateToken() {
		return generateCode(TOKEN_LENGTH);
	}
	
	/**
	 * Generates reandom code.
	 * 
	 * @param length		length of the code
	 * @return				generated code
	 */
	public static final String generateCode(int length) {
		StringBuilder token = new StringBuilder();
		for (int i = 0; i < TOKEN_LENGTH; i++) {
			token.append(ALOWED_CHARACTERS[RANDOM.nextInt(TOKEN_LENGTH)]);
		}
			
		return token.toString();
	}
}
