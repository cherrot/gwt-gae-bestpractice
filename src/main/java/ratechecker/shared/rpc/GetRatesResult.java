/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.shared.rpc;

import ratechecker.shared.models.Rate;
import java.util.List;
import net.customware.gwt.dispatch.shared.Result;

/**
 *
 * @author cherrot
 */
public class GetRatesResult implements Result {

	private static final long serialVersionUID = 9126251673298953395L;

	private List<Rate> _rates;

	public GetRatesResult(final List<Rate> rates) {
		_rates = rates;
	}

	public GetRatesResult() {
	}

	public List<Rate> getRates() {
		return _rates;
	}

	public void setRates(final List<Rate> rates) {
		_rates = rates;
	}

}