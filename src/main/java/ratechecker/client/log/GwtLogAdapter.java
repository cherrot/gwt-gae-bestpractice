/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.log;

import com.allen_sauer.gwt.log.client.Log;
/**
 *
 * @author cherrot
 */
public class GwtLogAdapter implements ILog {

	public GwtLogAdapter() {
	}

	@Override
	public void debug(final String msg) {
		Log.debug(msg);
	}

	@Override
	public void error(final String msg) {
		Log.error(msg);
	}

	@Override
	public void fatal(final String msg) {
		Log.fatal(msg);
	}

	@Override
	public void info(final String msg) {
		Log.info(msg);
	}

	@Override
	public void trace(final String msg) {
		Log.trace(msg);
	}

	@Override
	public void warn(final String msg) {
		Log.warn(msg);
	}

}