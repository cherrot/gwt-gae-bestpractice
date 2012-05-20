/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server.guice;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * This code is borrowed from Hupa.
 * The responsibility of this servlet context listener is to construct
 * an injector (achieved by the last method).
 * Here, our injector contains two modules:
 * @see ratechecker.server.guice.GuiceServerModule
 * @see ratechecker.server.guice.DispatchServletModule
 * @author cherrot
 */
public class GuiceServletConfig extends GuiceServletContextListener {

	private ServletContext _ctx;

	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
		_ctx = null;
		super.contextDestroyed(servletContextEvent);
	}

	@Override
	public void contextInitialized(final ServletContextEvent servletContextEvent) {
		_ctx = servletContextEvent.getServletContext();
		super.contextInitialized(servletContextEvent);
	}

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new GuiceServerModule(), new DispatchServletModule());
	}

}
