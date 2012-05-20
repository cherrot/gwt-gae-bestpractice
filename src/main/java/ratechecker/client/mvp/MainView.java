/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.mvp;

import ratechecker.shared.models.Rate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * View in MVP pattern
 * It has no knowledge of which presenter triggered it or what model the data originates from.
 * @author cherrot
 */
public class MainView extends Composite implements MainPresenter.Display {

	private static MainViewUiBinder uiBinder = GWT
	.create(MainViewUiBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	@UiField
	Button refresh;

	@UiField
	Button fetchLatest;

	@UiField
	Label rateDisplay;

	@UiField
	FlexTable rateTable;

	@UiField
	Image loading;

	private final DateTimeFormat _dateTimeFormat;

	@Inject
	public MainView(final DateTimeFormat dateTimeFormat) {
		_dateTimeFormat = dateTimeFormat;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getFetchLatest() {
		return fetchLatest;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasText getRateDisplayLabel() {
		return rateDisplay;
	}

	@Override
	public void setEnabledFetchLatestButton(final boolean isEnabled) {
		fetchLatest.setEnabled(isEnabled);
	}

	@Override
	public void addToRecentRates(final Rate rate, final boolean toHead) {
		final int newRowIdx = toHead ? 0 : rateTable.getRowCount();
		rateTable.insertRow(newRowIdx);
		rateTable.setText(newRowIdx, 0, _dateTimeFormat.format(rate.getTimeFetched()));
		rateTable.setText(newRowIdx, 1, String.valueOf(rate.getRate()));
	}

	@Override
	public void clearRecentRates() {
		rateTable.removeAllRows();
	}

	@Override
	public HasClickHandlers getRefresh() {
		return refresh;
	}

	@Override
	public void setShowLoadingCurrentRate(final boolean isLoading) {
		loading.setVisible(isLoading);
		rateDisplay.setVisible(!isLoading);
	}

    /**
     *
     * UPGRADE to gwt-presenter 1.1.1
     *
     * This method is removed since gwt-presenter 1.1.1
     * http://borglin.net/gwt-project/?page_id=212
     * http://borglin.net/gwt-project/?page_id=383
     */
//    @Override
//    public void startProcessing() { }
    /**
     * UPGRADE to gwt-presenter 1.1.1
     */
//	@Override
//    public void stopProcessing() { }
}

