/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.Result;
import org.apache.commons.logging.Log;
//import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

/**
 * This servlet extends from GWT-dispatch’s GuiceStandardDispatchServlet.
 * It’s main responsibility is to provide unified logging.
 *
 * DispatchServiceServlet has been replaced with either the
 * GuiceStandardDispatchServlet or the
 * GuiceSecureDispatchServlet depending on needs.
 * @author cherrot
 */
@Singleton
public class RateCheckerDispatchServlet extends GuiceStandardDispatchServlet{

	private static final long serialVersionUID = 4895255235709260169L;

	private final Log _logger;

    /**
     *
     * @param dispatch
     * @param logger The logger will be inject by LogProvider.get(). because this is bind in GuiceServerModule.
     * @see ratechecker.server.guice.LogProvider
     * @see ratechecker.server.guice.GuiceServerModule
     */
	@Inject
	public RateCheckerDispatchServlet(final Dispatch dispatch, final Log logger) {
		super(dispatch);
		_logger = logger;
	}

	@Override
	public Result execute(final Action<?> action) throws ActionException {
		try {
			_logger.info("executing: " + action.getClass().getName());
			final Result res = super.execute(action);
			_logger.info("finished: " + action.getClass().getName());
			return res;
		} catch (final ActionException ae) {
			_logger.error(ae.getMessage());
			ae.printStackTrace();
			throw ae;
		} catch (final Exception e) {
			_logger.error("Unexpected exception: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}

