package org.amadeus.inj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MyBeanExecutor implements IBean{
	
	Logger LOG=LoggerFactory.getLogger(MyBeanExecutor.class);
	public void start(IWorkflowListener listener) {
		
		LOG.info("Executor bean start @ "+ new java.util.Date());

		MyRunnable runnable = new MyRunnable(listener);
//		runnable.run(); // this works (sync mode)
		
//		this fails (async mode)
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(runnable);		
		es.shutdown();

		LOG.info("Executor bean stop @ "+ new java.util.Date());
	}
}