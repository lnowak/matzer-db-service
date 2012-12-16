package com.matzer.db.commons.dbunit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.dbunit.DatabaseUnitException;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * 
 * Abstract class for providing the DbUnit commons methods.
 * 
 * @author lkawon@gmail.com
 *
 */
public abstract class AbstractDbUnitTests extends AbstractTransactionalTestNGSpringContextTests {

	/**
	 * Entity manager factory.
	 */
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	/**
	 * XML dataset files with dbunit content.
	 */
	private List<String> xmlDataSets = new ArrayList<String>();
	
	
	/**
	 * @return the xmlDataSets
	 */
	public final List<String> getXmlDataSets() {
		return xmlDataSets;
	}

	/**
	 * @param xmlDataSets the xmlDataSets to set
	 */
	public final void setXmlDataSets(List<String> xmlDataSets) {
		this.xmlDataSets = xmlDataSets;
	}

	/**
	 * Sets up the data for the test.
	 * 
	 * @throws DatabaseUnitException	
	 * @throws SQLException	
	 * @throws IOException	
	 */
	@BeforeClass
	public final void prepareDatabase() throws DatabaseUnitException, SQLException, IOException {	
		for (String dataset : xmlDataSets) {
			DatabaseOperation.CLEAN_INSERT.execute(
					DatabaseCommons.getConnection(entityManagerFactory), 
					DatabaseCommons.getDataSet(this, dataset));
		}
	}	
	
	/**
	 * Deletes all records from DB.
	 * 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws DatabaseUnitException 
	 */
	@AfterClass
	public final void clearDatabase() throws DatabaseUnitException, SQLException, IOException {
		for (int i = xmlDataSets.size() - 1; i >= 0; i--) {
		DatabaseOperation.DELETE_ALL.execute(DatabaseCommons.getConnection(entityManagerFactory), 
				DatabaseCommons.getDataSet(this, xmlDataSets.get(i)));
		}		
	}
}
