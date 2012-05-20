/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;
import net.customware.gwt.presenter.client.place.ParameterTokenFormatter;
import net.customware.gwt.presenter.client.place.PlaceManager;
import net.customware.gwt.presenter.client.place.TokenFormatter;
import ratechecker.client.bundles.RateCheckerClientBundle;
import ratechecker.client.log.GwtLogAdapter;
import ratechecker.client.log.ILog;
import ratechecker.client.mvp.AppPresenter;
import ratechecker.client.mvp.MainPresenter;
import ratechecker.client.mvp.MainView;

/**
 *
 * @author cherrot
 */
public class RateCheckerClientModule extends AbstractPresenterModule {

	public RateCheckerClientModule() {
	}

    /**
     * To start up, we bind EventBus and PlaceManager in the singleton scope.
     * They’re both provided by GWT-mvp library.
     *
     * After binding, we can use \@inject in presenter constructors.
     */
	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
        /**
         * UPGRADE GWT-presenter from 1.0.0 to 1.1.1
         */
//		bind(PlaceManager.class).in(Singleton.class);
        bind(TokenFormatter.class).to(ParameterTokenFormatter.class);
        bind(PlaceManager.class).to(BasicPlaceManager.class).asEagerSingleton();

        bind(ILog.class).to(GwtLogAdapter.class).in(Singleton.class);
        bind(AppPresenter.class);

        bindPresenter(MainPresenter.class, MainPresenter.Display.class, MainView.class);
	}

    @Provides DateTimeFormat getDateTimeFormat() {
		return DateTimeFormat.getShortDateTimeFormat();
	}

	@Provides RateCheckerClientBundle getRateCheckerClientBundle() {
		return GWT.create(RateCheckerClientBundle.class);
	}
}

