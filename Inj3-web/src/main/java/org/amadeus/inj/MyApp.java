package org.amadeus.inj;

import org.apache.wicket.Page;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.protocol.http.WebApplication;

public class MyApp extends WebApplication{

	public static MyApp get()
	{
		return (MyApp) WebApplication.get();
	}
	
	@Override
	protected void init() {
		super.init();
		
		new CdiConfiguration().configure(this);
		
		
	}
	@Override
	public Class<? extends Page> getHomePage() {
		return MyPage.class;
	}

}
