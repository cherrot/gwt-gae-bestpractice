/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server.guice;

import com.google.inject.servlet.ServletModule;
import ratechecker.server.AutoFetchServlet;
import ratechecker.server.RateCheckerDispatchServlet;

/**
 * This module has a mapping of URIs and its serving classes.
 * It serves “/ratechecker/dispatch” with RateCheckerDispatchServlet,
 * which is the entry point for GWT-dispatch.
 * @see ratechecker.server.RateCheckerDispatchServlet
 * @author cherrot
 */
public class DispatchServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        super.configureServlets();
        serve("/ratechecker/dispatch").with(RateCheckerDispatchServlet.class);
        serve("/ratechecker/crons/autofetch").with(AutoFetchServlet.class);
    }
}

