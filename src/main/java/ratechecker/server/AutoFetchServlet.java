/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server;

/**
 *
 * @author cherrot
 */
import ratechecker.shared.models.RateType;
import ratechecker.shared.rpc.CheckRateAction;
import ratechecker.shared.rpc.CheckRateResult;
import ratechecker.shared.rpc.SaveRateAction;
import ratechecker.shared.rpc.SaveRateResult;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;
import org.apache.commons.logging.Log;

@Singleton
public class AutoFetchServlet extends HttpServlet {

	private static final long serialVersionUID = 7409775561580549055L;

	private final Dispatch _dispatch;

	private final Log _logger;

	@Inject
	public AutoFetchServlet(final Dispatch dispatch, final Log logger) {
		_dispatch = dispatch;
		_logger = logger;
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		final CheckRateAction checkRate = new CheckRateAction(RateType.Selling);
		try {
			final CheckRateResult checkRateResult = _dispatch.execute(checkRate);
			final SaveRateAction saveRate = new SaveRateAction(checkRateResult.getRate());
			final SaveRateResult saveRateResult = _dispatch.execute(saveRate);
			if (!saveRateResult.isSuccess()) {
				throw new ActionException("SaveRate not successful");
			}
		} catch (final DispatchException e) {
			e.printStackTrace();
		}

	}

}