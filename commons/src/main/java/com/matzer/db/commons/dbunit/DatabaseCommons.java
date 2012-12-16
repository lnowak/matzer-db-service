package com.matzer.db.commons.dbunit;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * 
 * Utility class for commons methods used for testing.
 * 
 * @author lkawon@gmail.com
 *
 */
public final class DatabaseCommons {

	/**
	 * Hidden constructor.
	 */
	private DatabaseCommons() {
		
	}
	
	/**
	 * Gets data set from classpath.
	 * 
	 * @return data set	
	 * 					
	 * @throws DataSetException	
	 */
	public static IDataSet getDataSet(AbstractTransactionalTestNGSpringContextTests unitTest, String xmlDataSet) 
			throws DataSetException {
		InputStream inputStream = unitTest.getClass().getClassLoader().getResourceAsStream(xmlDataSet);
		IDataSet dataset = new FlatXmlDataSetBuilder().build(inputStream);
		return dataset;
	}
	
	/**
	 * Gets database connection from the {@link EntityManagerFactory}.
	 * 
	 * @return JDBC connection
	 * 
	 * @throws DatabaseUnitException 
	 * @throws SQLException 
	 */
	@SuppressWarnings("deprecation")
	public static IDatabaseConnection getConnection(EntityManagerFactory entityManagerFactory) 
			throws DatabaseUnitException, SQLException {
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection connection = cp.getConnection();
		
		return new DatabaseConnection(connection);
	}
}
