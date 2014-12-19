package org.amadeus.inj;

import java.util.concurrent.Future;

import javax.ejb.Local;

public interface IMyBeanAsync {
	public Future<String> start();
	public void pushMessage(IWorkflowListener listener);
	public void pushMessage();
}
