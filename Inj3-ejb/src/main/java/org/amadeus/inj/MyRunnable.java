package org.amadeus.inj;

import org.jboss.logging.Logger;

//Runnable implementation
public class MyRunnable implements Runnable {
	Logger LOG=Logger.getLogger(MyRunnable.class);
	
	public MyRunnable(IWorkflowListener listener) {
		this.listener = listener;
	}
	
	private final IWorkflowListener listener;

	public void run() {
		LOG.info("Runnable start @ "+ new java.util.Date());
		try {
			Thread.sleep(5000); // 1000 milliseconds is one second.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		//Push message to listener
		this.listener.onMessage("Stopped Runnable" + new java.util.Date());
		LOG.info("Runnable end @ "+ new java.util.Date());		
	}
}