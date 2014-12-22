package org.amadeus.inj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Runnable implementation
public class MyRunnable implements Runnable {
	IWorkflowListener listener;	
	Logger LOG=LoggerFactory.getLogger(MyRunnable.class);
	
	public MyRunnable(IWorkflowListener listener) {
		this.listener = listener;
	}
	
	public void run() {
		LOG.info("Runnable start @ "+ new java.util.Date());
		try {
			Thread.sleep(5000); // 1000 milliseconds is one second.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		//Push message to listener
		this.listener.onMessage("Runnable end @ " + new java.util.Date());
		LOG.info("Runnable end @ "+ new java.util.Date());		
	}
}