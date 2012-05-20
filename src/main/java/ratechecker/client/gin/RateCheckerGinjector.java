/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.gin;

import ratechecker.client.mvp.AppPresenter;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import net.customware.gwt.dispatch.client.gin.StandardDispatchModule;
//import net.customware.gwt.dispatch.client.gin.ClientDispatchModule;

/**
 * Similar to “Injector” interface on the server side,
 * the client side needs to define a Ginjector that act as a gateway for Gin managed object instances.
 *
 * Here the annotation \@GinModules({…}) makes the instances managed by
 * RateCheckerClientModule and StandardDispatchModule available for the ginjector.
 * StandardDispatchModule binds DispatchAsync interface,
 * which is what we will use to interface with the web service methods.
 *
 * Similar to RateCheckerDispatchServlet on the server side using Google-Guice
 * ClientDispatchModule on the client side using Google-Gin has been
 * replaced by StandardDispatchModule or SecureDispatchModule
 *
 * @see ratechecker.server.RateCheckerDispatchServlet
 * @author cherrot
 */

@GinModules({RateCheckerClientModule.class, StandardDispatchModule.class})
public interface RateCheckerGinjector extends Ginjector {

    /**
     *  defined getters for the instances that we need to explicitly fetch
     */
	AppPresenter getAppPresenter();
}
