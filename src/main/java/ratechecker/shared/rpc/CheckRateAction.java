/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.shared.rpc;

import ratechecker.shared.models.RateType;
import net.customware.gwt.dispatch.shared.Action;

/**
 * Both action and result are serialized and sent over the wire as part of GWT-RPC call,
 * they are required to have a default public constructor.
 *
 * @see ratechecker.shared.rpc.CheckRateResult
 * @see ratechecker.server.handlers.CheckRateHandler
 * @author cherrot
 */
public class CheckRateAction implements Action<CheckRateResult> {

	private static final long serialVersionUID = -1716760883016361503L;

	private RateType _type;

	public CheckRateAction() {
	}

	public CheckRateAction(final RateType type) {
		_type = type;
	}

	public void setType(final RateType type) {
		_type = type;
	}

	public RateType getType() {
		return _type;
	}

}
