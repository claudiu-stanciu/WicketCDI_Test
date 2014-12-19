package org.amadeus.inj;

import javax.ejb.Asynchronous;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateful
@Asynchronous
public class BeanSockets implements IBeanSockets{
	Logger LOG=LoggerFactory.getLogger(BeanSockets.class);
	
	@Override
	public void pushMessage(IWorkflowListener listener) {
		try {
			LOG.info("CDI Async bean start @ "+ new java.util.Date());
			Thread.sleep(5000);
			LOG.info("CDI Async bean end @ "+ new java.util.Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("CDI Async bean end good @ "+ new java.util.Date());
		listener.onMessage("CDI Async bean end @ "+ new java.util.Date());		
	}
	@Override
	public void pushMessage(){
		try {
			LOG.info("CDI Async bean start @ "+ new java.util.Date());
			Thread.sleep(5000);
			LOG.info("CDI Async bean end @ "+ new java.util.Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("CDI Async bean end good @ "+ new java.util.Date());
	}
}
