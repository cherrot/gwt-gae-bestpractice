/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.shared.events;

import ratechecker.shared.models.Rate;
import com.google.gwt.event.shared.GwtEvent;

/**
 *
 * @author cherrot
 */
public final class RateSavedEvent extends GwtEvent<RateSavedEventHandler> {

	public static final GwtEvent.Type<RateSavedEventHandler> TYPE = new GwtEvent.Type<RateSavedEventHandler>();

	private final Rate _rate;

	public RateSavedEvent(final Rate rate) {
		_rate = rate;
	}


	@Override
	protected void dispatch(final RateSavedEventHandler handler) {
		handler.onRateSaved(_rate);
	}

	@Override
	public GwtEvent.Type<RateSavedEventHandler> getAssociatedType() {
		return TYPE;
	}

	public Rate getRate() {
		return _rate;
	}
}