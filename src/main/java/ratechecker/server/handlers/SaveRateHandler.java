/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server.handlers;

import ratechecker.server.guice.PersistenceManagerProvider;
import ratechecker.shared.models.Rate;
import ratechecker.shared.rpc.SaveRateAction;
import ratechecker.shared.rpc.SaveRateResult;
import com.google.inject.Inject;
import javax.jdo.PersistenceManager;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

/**
 *
 * @author cherrot
 */
public class SaveRateHandler implements ActionHandler<SaveRateAction, SaveRateResult> {

	private final PersistenceManagerProvider _pmp;
	private final RecentRatesCache _recentRatesCache;

	@Inject
	public SaveRateHandler(final PersistenceManagerProvider pmp, final RecentRatesCache recentRatesCache) {
		_pmp = pmp;
		_recentRatesCache = recentRatesCache;
	}

	@Override
	public SaveRateResult execute(final SaveRateAction action, final ExecutionContext ctx)
	throws ActionException {

		final Rate rateToSave = action.getRate();

		_recentRatesCache.addToCache(rateToSave);

		final SaveRateResult retval = new SaveRateResult();

		final PersistenceManager pm = _pmp.get();
		try {
			pm.makePersistent(rateToSave);
			retval.setSuccess(true);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public Class<SaveRateAction> getActionType() {
		return SaveRateAction.class;
	}

	@Override
	public void rollback(final SaveRateAction action, final SaveRateResult result,
			final ExecutionContext ctx) throws ActionException {

	}

	private void _updateMemcache() {

	}
}