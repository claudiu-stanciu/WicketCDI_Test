package org.amadeus.inj;

import javax.ejb.Asynchronous;
import javax.ejb.Stateful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateful
@Asynchronous
public class MyEJB implements IMyEJB{
	Logger LOG=LoggerFactory.getLogger(MyEJB.class);
	
	public void start(IWorkflowListener listener) {		
		LOG.info("EJB Async bean start @ "+ new java.util.Date());

		try {
			// Processing a long job
			Thread.sleep(5000); // 1000 milliseconds is one second.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		//Push message to UI when job ends
		listener.onMessage("EJB Async bean end @ " + new java.util.Date());

		LOG.info("EJB Async bean stop @ "+ new java.util.Date());
	}
}