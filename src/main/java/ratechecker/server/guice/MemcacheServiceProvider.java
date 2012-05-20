/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratechecker.server.guice;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 *
 * @author cherrot
 */
@Singleton
public class MemcacheServiceProvider implements Provider<MemcacheService> {

	public MemcacheServiceProvider() {
	}

	@Override
	public MemcacheService get() {
		return MemcacheServiceFactory.getMemcacheService();
	}

}
