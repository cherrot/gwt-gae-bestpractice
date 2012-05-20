/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.log;

/**
 *
 * @author cherrot
 */
public interface ILog {

	void trace(String msg);
	void info(String msg);
	void debug(String msg);
	void warn(String msg);
	void error(String msg);
	void fatal(String msg);
}