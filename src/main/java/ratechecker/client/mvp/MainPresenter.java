/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.mvp;

import ratechecker.client.log.ILog;
import ratechecker.shared.events.RateFetchedEvent;
import ratechecker.shared.events.RateFetchedEventHandler;
import ratechecker.shared.events.RateSavedEvent;
import ratechecker.shared.events.RateSavedEventHandler;
import ratechecker.shared.models.Rate;
import ratechecker.shared.models.RateType;
import ratechecker.shared.rpc.CheckRateAction;
import ratechecker.shared.rpc.CheckRateResult;
import ratechecker.shared.rpc.GetRatesAction;
import ratechecker.shared.rpc.GetRatesResult;
import ratechecker.shared.rpc.SaveRateAction;
import ratechecker.shared.rpc.SaveRateResult;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;
//import net.customware.gwt.presenter.client.place.Place;
//import net.customware.gwt.presenter.client.place.PlaceRequest;

/**
 * Many methods are updated in gwt-presenter 1.1.1 :
 * http://borglin.net/gwt-project/?page_id=212
 * http://borglin.net/gwt-project/?page_id=383
 * @author cherrot
 */
public class MainPresenter extends WidgetPresenter<MainPresenter.Display> {

	/**
     * The display interface is the only thing presenter knows about the UI,
     * and the presenter operates/manipulates UI only through the display interface.
     *
     * The display interface can be standalone,
     * but I find it’s much more convenient to have it as an inner interface
     * inside the presenter class.
     */
    public interface Display extends WidgetDisplay {
        /**
         * Here we use the “characteristic interface” of the UI elements as return type as they can be mocked.
         * For things that cannot be returned as characteristic interfaces (like FlexTable),
         * we provide methods for the presenter to manipulate the state of the UI objects (such as clearRecentRates()).
         */
		HasText getRateDisplayLabel();
		HasClickHandlers getFetchLatest();
		HasClickHandlers getRefresh();

		void setEnabledFetchLatestButton(boolean isEnabled);
		void setShowLoadingCurrentRate(boolean isLoading);

        /**
		 * Add the rate to the recent rate table.
		 * @param rate
		 * 		The {@link Rate} object
		 * @param toHead
		 * 		<code>true</code> - rate is inserted to the beginning of the table
		 * 		<code>false</code> - rate is appended to the end of the table
		 */
		void addToRecentRates(Rate rate, boolean toHead);
		/**
		 * Clear the recent rates table.
		 */
		void clearRecentRates();
	}

	private final DispatchAsync _dispatch;

	private final ILog _logger;

    /**
     * The presenter is also responsible for making web service calls and
     * deal with the returns. To call GWT-RPC web service using GWT-dispatch,
     * we inject a DispatchAsync, which is an asynchronous counter part of
     * the DispatchServlet introduced a few posts ago.
     * @see ratechecker.server.RateCheckerDispatchServlet
     */
	@Inject
	public MainPresenter(final Display display,	final EventBus eventBus,
                         final DispatchAsync dispatch, final ILog logger) {

        //A presenter constructor always takes at least a Display and EventBus as parameters
		super(display, eventBus);
		_dispatch = dispatch;
		_logger = logger;
        //TODO Is it necessary?
//        bind();
	}

	@Override
	protected void onBind() {
		registerHandler(display.getFetchLatest().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				fetchSellingRate();
			}

		}));

		registerHandler(eventBus.addHandler(RateFetchedEvent.TYPE, new RateFetchedEventHandler() {

			@Override
			public void onRateFetched(final Rate rate) {
				saveRate(rate);
			}

		}));

		registerHandler(eventBus.addHandler(RateSavedEvent.TYPE, new RateSavedEventHandler() {

			@Override
			public void onRateSaved(final Rate rate) {
				display.addToRecentRates(rate, true);
			}

		}));

		registerHandler(display.getRefresh().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				getLatestSavedRates();
			}

		}));

		getLatestSavedRates();
	}

	void getLatestSavedRates() {
		display.setShowLoadingCurrentRate(true);

		final GetRatesAction getRatesAction = new GetRatesAction();

		_dispatch.execute(getRatesAction, new AsyncCallback<GetRatesResult>() {

			@Override
			public void onFailure(final Throwable caught) {
				display.setShowLoadingCurrentRate(false);
				_logger.error("Unable to get saved rates: " + caught.getMessage());
			}

			@Override
			public void onSuccess(final GetRatesResult result) {
				display.setShowLoadingCurrentRate(false);
				display.clearRecentRates();

				for (final Rate rate : result.getRates()) {
					display.addToRecentRates(rate, false);
				}

				// Put the latest rate in the box
				if (result.getRates().size() > 0) {
					final Rate latestRate = result.getRates().get(0);
					display.getRateDisplayLabel().setText(String.valueOf(latestRate.getRate()));
				}
			}
		});

	}

	void fetchSellingRate() {
		display.setShowLoadingCurrentRate(true);
		final CheckRateAction checkRateAction = new CheckRateAction(RateType.Selling);
		_dispatch.execute(checkRateAction, new AsyncCallback<CheckRateResult>() {

			@Override
			public void onFailure(final Throwable caught) {
				display.setShowLoadingCurrentRate(false);
				_logger.error("Unable to fetch rate: " + caught.getMessage());
			}

			@Override
			public void onSuccess(final CheckRateResult result) {
				display.setShowLoadingCurrentRate(false);
				// enable the fetch button
				display.setEnabledFetchLatestButton(true);
				display.getRateDisplayLabel().setText(String.valueOf(result.getRate().getRate()));
				eventBus.fireEvent(new RateFetchedEvent(result.getRate()));
			}

		});

		// disable the fetch button until RPC succeeds
		display.setEnabledFetchLatestButton(false);
	}

	void saveRate(final Rate rate) {
		final SaveRateAction saveRate = new SaveRateAction(rate);

		_dispatch.execute(saveRate, new AsyncCallback<SaveRateResult>() {

			@Override
			public void onFailure(final Throwable caught) {
				_logger.error("Unable to save rate: " + caught.getMessage());
			}

			@Override
			public void onSuccess(final SaveRateResult result) {
				eventBus.fireEvent(new RateSavedEvent(rate));
			}

		});

	}

    /**
     * UPGRADE to gwt-presenter 1.1.1
     *
     * When a place request has been fired (either via history or "manually"),
     * we get a callback here
     */
//	@Override
//	protected void onPlaceRequest(final PlaceRequest request) {
//		// TODO Auto-generated method stub
//	}

	@Override
	protected void onUnbind() {
	}

    /**
     * This method is removed since gwt-prensenter 1.1.1 since it does nothing.
     */
//	@Override
//	public void refreshDisplay() {
//	}

//	@Override
//	public void revealDisplay() {
//	}

    @Override
    protected void onRevealDisplay() {
    }

    /**
     * UPGRADE to gwt-presenter 1.1.1
     */
//	@Override
//	public Place getPlace() {
//		return null;
//	}
}
