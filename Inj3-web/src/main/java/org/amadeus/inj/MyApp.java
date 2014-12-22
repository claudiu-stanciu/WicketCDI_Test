package org.amadeus.inj;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.javaee.injection.JavaEEComponentInjector;

public class MyApp extends WebApplication{

	public static MyApp get()
	{
		return (MyApp) WebApplication.get();
	}
	
	@Override
	protected void init() {
		super.init();
		
		//new CdiConfiguration().configure(this);
		getComponentInstantiationListeners().add(new JavaEEComponentInjector(this));
		
	}
	@Override
	public Class<? extends Page> getHomePage() {
		return MyPage.class;
	}

}
