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

public final class RateFetchedEvent extends GwtEvent<RateFetchedEventHandler> {

	public static final Type<RateFetchedEventHandler> TYPE = new Type<RateFetchedEventHandler>();

	private final Rate _rate;

	public RateFetchedEvent(final Rate rate) {
		_rate = rate;
	}

	@Override
	protected void dispatch(final RateFetchedEventHandler handler) {
		handler.onRateFetched(_rate);
	}

	@Override
	public GwtEvent.Type<RateFetchedEventHandler> getAssociatedType() {
		return TYPE;
	}

}