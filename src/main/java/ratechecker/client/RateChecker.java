/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import ratechecker.client.gin.RateCheckerGinjector;
import ratechecker.client.mvp.AppPresenter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RateChecker implements EntryPoint {

	/**
     * GWT.create(…) statement creates the ginjector at runtime.
     * Behind the scene, it generates a class (by the name of something like
     * RateCheckerGinjector_Impl) that contains the code to instantiate the bound classes,
     * and when Gin sees a \@Inject annotation on a class’s constructor,
     * it provides the instances with the correct scope from the dependency injection container (Ginjector)
     * to the constructor so that the said class can be instantiated.
     *
     * GWT.create(…) method is also used to create the bound class of i18n interface.
     */
    RateCheckerGinjector _injector = GWT.create(RateCheckerGinjector.class);

    /**
     * bind the appPresenter with the RootPanel where the app’s UI is going to be displayed.
     */
	@Override
	public void onModuleLoad() {

		final AppPresenter appPresenter = _injector.getAppPresenter();
		appPresenter.go(RootPanel.get("root"));
	}

}
