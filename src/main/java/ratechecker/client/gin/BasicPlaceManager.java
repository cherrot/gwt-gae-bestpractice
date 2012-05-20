/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.gin;

import com.google.inject.Inject;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.DefaultPlaceManager;
import net.customware.gwt.presenter.client.place.TokenFormatter;

/**
 * UPGRADE from GWT-presenter from 1.0.0 to 1.1.1
 * @author cherrot
 */
public class BasicPlaceManager extends DefaultPlaceManager {

    @Inject
    public BasicPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter) {
        super(eventBus, tokenFormatter);
    }

}
