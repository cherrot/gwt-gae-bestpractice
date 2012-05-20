/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.client.mvp;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

/**
 * There are different ways to facilitate the MVP pattern but the way I find
 * the most convenient is to have an AppPresenter manage all subsequent presenters.
 * The view the AppPresenter represents is the RootPanel of GWT.
 *
 * In the binding process, the event handlers are attached to the view components.
 * MainPresenter.bind() was explicitly called by AppPresenter.go().
 * This is a simple application with one presenter. If there are more presenters,
 * AppPresenter needs to manage the state of these sub-presenters:
 * if they’re active, the bind() method is called.
 * If the presenter is no-longer active, the presenter’s unbind() method should be called
 * to un-attach the handlers, so they don’t interfere with the event handlers
 * that are currently in the active presenter.
 * @author cherrot
 */
public class AppPresenter {

	private HasWidgets _container;

	private final MainPresenter _mainPresenter;


    /**
     *
     * @param mainPresenter MainPresenter is the actual UI.
     */
	@Inject
	public AppPresenter(final MainPresenter mainPresenter) {
		_mainPresenter = mainPresenter;
		_mainPresenter.bind();
	}

    /**
     * The go() method of AppPresenter is for the module entry point to call
     * when the module first initializes
     * @param container
     */
	public void go(final HasWidgets container) {
		_container = container;
		_container.clear();
		_container.add(_mainPresenter.getDisplay().asWidget());
	}
}

