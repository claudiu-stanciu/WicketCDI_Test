package org.amadeus.inj;

import javax.ejb.Asynchronous;
import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
@Asynchronous
public class MyMessBean implements IBean{
	Logger LOG=LoggerFactory.getLogger(MyMessBean.class);
	public void pushMessage(IWorkflowListener listener){
		LOG.info("Runnable end from message bean @ "+ new java.util.Date());
		listener.onMessage("Runnable end @ " + new java.util.Date());
	}

}
