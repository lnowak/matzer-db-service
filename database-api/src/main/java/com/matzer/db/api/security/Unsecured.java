package com.matzer.db.api.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * Used to inform that the class is used for authentication process.
 * 
 * @author lkawon@gmail.com
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Unsecured {

}
