/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.shared.rpc;

import ratechecker.shared.models.Rate;
import net.customware.gwt.dispatch.shared.Result;

/**
 * The result is designed to hold the returned Rate object.
 *
 * both action and result are serialized and sent over the wire as part of GWT-RPC call,
 * they are required to have a default public constructor.
 *
 * @see ratechecker.shared.rpc.CheckRateAction
 * @see ratechecker.server.handlers.CheckRateHandler
 * @author cherrot
 */
public class CheckRateResult implements Result {

	private static final long serialVersionUID = -9099789297842581458L;

	private Rate _rate;

	public CheckRateResult() {
	}

	public CheckRateResult(final Rate rate) {
		_rate = rate;
	}

	public void setRate(final Rate rate) {
		_rate = rate;
	}

	public Rate getRate() {
		return _rate;
	}
}

