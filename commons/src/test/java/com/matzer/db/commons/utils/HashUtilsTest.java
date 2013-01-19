package com.matzer.db.commons.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.matzer.db.commons.utils.HashUtils.HashType;

/**
 * 
 * For testing the {@link HashUtils}.
 * 
 * @author lkawon@gmail.com
 *
 */
public class HashUtilsTest {

	/**
	 * Generates SHA hash.
	 */
	@Test
	public final void generateHash() {
		// when
		String shaHash = HashUtils.generateHash(HashType.SHA, "homer123");
		
		// then
		Assert.assertNotNull(shaHash);
		Assert.assertEquals(shaHash, "9c8b998021f01967df9c6a0c872289097b7caf61");
	}
	
	/**
	 * Tries to generate SHA hash from null.
	 */
	@Test
	public final void generateHashFromNull() {
		// when
		String shaHash = HashUtils.generateHash(HashType.SHA, null);
				
		// then
		Assert.assertNull(shaHash);
	}
}
