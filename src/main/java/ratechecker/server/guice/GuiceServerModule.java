/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server.guice;

import ratechecker.server.handlers.CheckRateHandler;
import ratechecker.server.handlers.GetRatesHandler;
import ratechecker.server.handlers.RecentRatesCache;
import ratechecker.server.handlers.SaveRateHandler;
import ratechecker.shared.rpc.CheckRateAction;
import ratechecker.shared.rpc.GetRatesAction;
import ratechecker.shared.rpc.SaveRateAction;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.inject.Singleton;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import org.apache.commons.logging.Log;

/**
 * This is where you declare your bindings for the application.
 * We’ll come back to this file frequently as the application develops.
 * @author cherrot
 */
public class GuiceServerModule extends ActionHandlerModule {

	public GuiceServerModule() {
	}

    /**
     * bind @inject instances.
     */
	@Override
	protected void configureHandlers() {
        bind(Log.class).toProvider(LogProvider.class).in(Singleton.class);
        bind(PersistenceManagerProvider.class).in(Singleton.class);

        bind(MemcacheService.class).toProvider(MemcacheServiceProvider.class).in(Singleton.class);
        bind(RecentRatesCache.class).in(Singleton.class);

        /**
         * UPGRADE to GWT-dispatch from 1.0.0 to 1.1.0
         */
//        bindHandler(CheckRateHandler.class);
//        bindHandler(SaveRateHandler.class);
//        bindHandler(GetRatesHandler.class);
        bindHandler(CheckRateAction.class, CheckRateHandler.class);
        bindHandler(SaveRateAction.class, SaveRateHandler.class);
        bindHandler(GetRatesAction.class, GetRatesHandler.class);
    }

}
