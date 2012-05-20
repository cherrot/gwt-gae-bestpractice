/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server.handlers;

import ratechecker.shared.models.Rate;
import ratechecker.shared.rpc.CheckRateAction;
import ratechecker.shared.rpc.CheckRateResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

/**
 * This is the Action Handler running in the serverside which accepts the action and returns the result.
 * This handler does the actual work of fetching the rate using URL Fetch service offered by Google App Engine.
 *
 * @see ratechecker.shared.rpc.CheckRateAction
 * @see ratechecker.shared.rpc.CheckRateResult
 * @author cherrot
 */
public class CheckRateHandler implements ActionHandler<CheckRateAction, CheckRateResult> {

	public static final String URL_BUY = "http://www.ingdirect.ca/en/datafiles/rates/usbuying.html";

	public static final String URL_SELL = "http://www.ingdirect.ca/en/datafiles/rates/usselling.html";

	public CheckRateHandler() {
	}

	@Override
	public CheckRateResult execute(final CheckRateAction action, final ExecutionContext ctx) throws ActionException {
		final CheckRateResult retval = new CheckRateResult();

		String strUrl = null;
		switch (action.getType()) {
		case Buying:
			strUrl = URL_BUY;
			break;
		case Selling:
			strUrl = URL_SELL;
			break;
		}

		try {
			final URL url = new URL(strUrl);

			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(url.openStream()));

				final double dRate = Double.parseDouble(br.readLine());

				final Rate rate = new Rate();
				rate.setRate(dRate);
				rate.setType(action.getType());
				rate.setTimeFetched(new Date());

				retval.setRate(rate);

			} finally {
				if (br != null)
					br.close();
			}
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			throw new ActionException(e);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new ActionException(e);
		} catch (final NumberFormatException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		return retval;
	}

    @Override
    public Class<CheckRateAction> getActionType() {
        return CheckRateAction.class;
    }

    @Override
    public void rollback(CheckRateAction a, CheckRateResult r, ExecutionContext ec) throws DispatchException {
        
    }
}
