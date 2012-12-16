package com.matzer.db.cache;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

/**
 * 
 * Reads cache statistics and logs them.
 * 
 * @author lnowak@gmail.com
 *
 */
public class CacheStatisticsReader extends Thread {

	/**
	 * Log4j logger.
	 */
	private static Logger LOG = Logger.getLogger(CacheStatisticsReader.class);
	
	/**
	 * Entity manager factory.
	 */
	private Map<String, EntityManagerFactory> entityManagerFactories;
	
	/**
	 * Indicates that the thread is running.
	 */
	private boolean isRunning; 
	
	/**
	 * Log delay.
	 */
	private long delay = 60 * 1000;
	
	
	/**
	 * Default constructor.
	 */
	public CacheStatisticsReader() {
		
	}

	/**
	 * @return the entityManagerFactories
	 */
	public final Map<String, EntityManagerFactory> getEntityManagerFactories() {
		return entityManagerFactories;
	}

	/**
	 * @param entityManagerFactories the entityManagerFactories to set
	 */
	public final void setEntityManagerFactories(
			Map<String, EntityManagerFactory> entityManagerFactories) {
		this.entityManagerFactories = entityManagerFactories;
	}

	/**
	 * @return the delay
	 */
	public final long getDelay() {
		return delay;
	}

	/**
	 * @param delay the delay to set
	 */
	public final void setDelay(long delay) {
		this.delay = delay;
	}

	public final void stopThread() {
		isRunning = false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public final void run() {		
		isRunning = true;
		while (isRunning) {
			if (entityManagerFactories != null && entityManagerFactories.size() > 0) {
				for (Entry<String, EntityManagerFactory> entry : entityManagerFactories.entrySet()) {
					
					EntityManagerFactoryInfo entityManagerFactoryInfo = (EntityManagerFactoryInfo) entry.getValue();
					EntityManagerFactory emf = entityManagerFactoryInfo.getNativeEntityManagerFactory();
					EntityManagerFactoryImpl emfImp = (EntityManagerFactoryImpl)emf;
					
					LOG.debug(entry.getKey() + " - " + emfImp.getSessionFactory().getStatistics());
				}
			}
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				LOG.error("Excpetion ocurred.", e);
			}
		}
	}	
}
