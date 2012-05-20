/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.bundles;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource;

/**
 *
 * @author cherrot
 */
public interface RateCheckerClientBundle extends ClientBundle {

	@Source("ajax-loader.gif")
	ImageResource loading();

}