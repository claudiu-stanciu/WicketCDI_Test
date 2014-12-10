package org.amadeus.inj;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.enterprise.context.RequestScoped;

import org.jboss.logging.Logger;
// CDI implementation with Asynchronous mode, and Future return
// This works, but is blocking the UI thread
@RequestScoped
@Asynchronous
public class MyBeanAsync implements IBean{
	Logger LOG=Logger.getLogger(MyBeanAsync.class);
	public Future<String> start(){
		try {
			LOG.info("CDI Async bean start @ "+ new java.util.Date()+" in thread "+ Thread.currentThread().getName());
			Thread.sleep(5000);
			LOG.info("CDI Async bean end @ "+ new java.util.Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<String>("CDI Async stopped @ " + new java.util.Date());
	}
	public void run(IWorkflowListener listener){
		try {
			LOG.info("CDI Async bean start @ "+ new java.util.Date()+" in thread "+ Thread.currentThread().getName());
			Thread.sleep(5000);
			LOG.info("CDI Async bean end @ "+ new java.util.Date()+" in thread "+ Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		listener.onMessage("CDI Async stopped @ " + new java.util.Date() +" in thread "+ Thread.currentThread().getName());
	}
	
}
