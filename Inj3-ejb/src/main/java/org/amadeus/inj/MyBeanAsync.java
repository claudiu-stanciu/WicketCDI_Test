package org.amadeus.inj;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateful
@RequestScoped
@Asynchronous
public class MyBeanAsync implements IMyBeanAsync{

	Logger LOG=LoggerFactory.getLogger(MyBeanAsync.class);
	public Future<String> start(){
		try {
			LOG.info("CDI Async bean start @ "+ new java.util.Date());
			Thread.sleep(5000);
			LOG.info("CDI Async bean end @ "+ new java.util.Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<String>("CDI Async stopped @ " + new java.util.Date());
	}
	
	public void pushMessage(IWorkflowListener listener){
		try {
			LOG.info("CDI Async bean with message start @ "+ new java.util.Date());
			Thread.sleep(5000);
			LOG.info("CDI Async bean with message end @ "+ new java.util.Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		listener.onMessage("CDI Message from bean @ "+ new java.util.Date());
	}	
	
	public void noMessage(){
		try {
			LOG.info("CDI Async bean without message start @ "+ new java.util.Date());
			Thread.sleep(5000);
			LOG.info("CDI Async bean without message end @ "+ new java.util.Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
