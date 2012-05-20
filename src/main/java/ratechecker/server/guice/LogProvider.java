/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server.guice;

import com.google.inject.Provider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

/**
 * This class is provide lazy initialization for the Log object to its users.
 *
 * Without this, Guice will complain that there’s no binding for
 * org.apache.commons.logging.Log,
 * which we declared as a dependency for RateCheckerDispatchServlet.
 *
 * The binding should be added to GuiceServerModule
 * @see ratechecker.server.RateCheckerDispatchServlet
 * @see ratechecker.server.guice.GuiceServerModule
 * @author cherrot
 */
public class LogProvider implements Provider<Log> {

	@Override
	public Log get() {
		return new Log4JLogger("RateCheckerLogger");
	}

}
