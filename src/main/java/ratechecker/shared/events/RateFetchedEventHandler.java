/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.shared.events;

import ratechecker.shared.models.Rate;
import com.google.gwt.event.shared.EventHandler;

/**
 *
 * @author cherrot
 */
public interface RateFetchedEventHandler extends EventHandler {

	void onRateFetched(Rate rate);

}
