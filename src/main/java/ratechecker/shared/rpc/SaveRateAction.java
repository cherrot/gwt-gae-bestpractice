/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.shared.rpc;

import ratechecker.shared.models.Rate;
import net.customware.gwt.dispatch.shared.Action;

/**
 *
 * @author cherrot
 */
public class SaveRateAction implements Action<SaveRateResult> {

	private static final long serialVersionUID = -1813374026093369349L;

	private Rate _rate;

	public SaveRateAction() {
	}

	public SaveRateAction(final Rate rate) {
		_rate = rate;
	}

	public Rate getRate() {
		return _rate;
	}

	public void setRate(final Rate rate) {
		_rate = rate;
	}

}
