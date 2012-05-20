/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server.guice;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

/**
 * This is the same idea as LogProvider.
 * It provides an instance of PersistenceManager,
 * which is used by the data store related action handlers to deal with data persistence.
 *
 * @see ratechecker.server.guice.LogProvider
 * @author cherrot
 */
@Singleton
public class PersistenceManagerProvider implements Provider<PersistenceManager> {

	private final PersistenceManagerFactory _pmf;

	public PersistenceManagerProvider() {
		_pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	}

	@Override
	public PersistenceManager get() {
		return _pmf.getPersistenceManager();
	}

}