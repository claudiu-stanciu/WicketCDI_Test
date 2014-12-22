package org.amadeus.inj;

import javax.annotation.ManagedBean;
import javax.ejb.Asynchronous;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@ManagedBean

@Stateful
@Asynchronous
public class MyEJB implements IMyEJB{
	Logger LOG=LoggerFactory.getLogger(MyEJB.class);
	
	public void start(IWorkflowListener listener) {		
		LOG.info("Executor bean start @ "+ new java.util.Date());

		try {
			Thread.sleep(5000); // 1000 milliseconds is one second.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		//Push message to listener
		listener.onMessage("EJB async bean end @ " + new java.util.Date());

		LOG.info("EJB async bean stop @ "+ new java.util.Date());
	}

}
