package org.amadeus.inj;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateful
@ApplicationScoped
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
			LOG.info("CDI Async bean start @ "+ new java.util.Date());
			Thread.sleep(5000);
			LOG.info("CDI Async bean end @ "+ new java.util.Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("CDI Async bean end good @ "+ new java.util.Date());
		listener.onMessage("CDI Async bean end @ "+ new java.util.Date());
	}	
	
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
