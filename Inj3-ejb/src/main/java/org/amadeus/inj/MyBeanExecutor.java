package org.amadeus.inj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.RequestScoped;

import org.jboss.logging.Logger;

@RequestScoped
public class MyBeanExecutor implements IBean{
	
	Logger LOG=Logger.getLogger(MyBeanExecutor.class);
	public void start(IWorkflowListener listener) {
		LOG.info("Executor bean start @ "+ new java.util.Date());
        ExecutorService es = Executors.newFixedThreadPool(3);
		es.execute(new MyRunnable(listener));		
		LOG.info("Executor bean stop @ "+ new java.util.Date());
		es.shutdown();
	}

	public String getString(){		
		return "Executor bean returned";
	}
}