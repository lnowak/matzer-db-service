package com.matzer.db.api;

import java.util.Arrays;

import org.testng.annotations.Test;

/**
 * 
 * For testing the {@link Main} class.
 * 
 * @author lkawon@gmail.com
 *
 */
public class MainTest {

	/**
	 * Starts the application.
	 */
	@Test
	public final void startApplication() {
		Main.main((String[]) Arrays.asList("spring-config-hsqldb.xml").toArray());
	}
}
