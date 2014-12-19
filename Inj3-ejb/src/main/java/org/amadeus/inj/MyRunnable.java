package org.amadeus.inj;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Runnable implementation
public class MyRunnable implements Runnable {
	
	@Inject
	MyMessBean myMessBean;
	
	Logger LOG=LoggerFactory.getLogger(MyRunnable.class);
	
	private final IWorkflowListener listener;
	
	public MyRunnable(IWorkflowListener listener) {
		this.listener = listener;
	}	
	
	public void run() {
		
//		CdiContainer cdiContainer=CdiContainerLoader.getCdiContainer();
//		cdiContainer.boot();
//		
//		ContextControl ctxCtrl=cdiContainer.getContextControl();
//		ctxCtrl.startContext(RequestScoped.class);
		
		LOG.info("Runnable start @ "+ new java.util.Date());
		try {
			Thread.sleep(5000); // 1000 milliseconds is one second.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		//Push message to listener
		//this.listener.onMessage("Runnable end @ " + new java.util.Date());
		LOG.info("Runnable end @ "+ new java.util.Date());
		myMessBean.pushMessage(listener);
//		ctxCtrl.stopContext(RequestScoped.class);
//		cdiContainer.shutdown();
	}
}