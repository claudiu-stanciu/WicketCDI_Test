package org.amadeus.inj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ejb.Asynchronous;
import javax.enterprise.context.RequestScoped;

import org.jboss.logging.Logger;

@RequestScoped
@Asynchronous
public class MyBeanExecutor implements IBean{
	
	Logger LOG=Logger.getLogger(MyBeanExecutor.class);
	public void start(IWorkflowListener listener) {
		
		LOG.info("Executor bean start @ "+ new java.util.Date() +" in thread "+ Thread.currentThread().getName());

		MyRunnable runnable = new MyRunnable(listener);
		runnable.run(); // this works (sync mode)
		
//		this fails (async mode)
//      ExecutorService es = Executors.newSingleThreadExecutor();
//		es.execute(runnable);		
//		es.shutdown();

		LOG.info("Executor bean stop @ "+ new java.util.Date() +" in thread "+ Thread.currentThread().getName());
	}

	public String getString(){		
		return "Executor bean returned";
	}
}