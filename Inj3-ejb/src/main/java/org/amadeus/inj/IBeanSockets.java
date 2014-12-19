package org.amadeus.inj;

import javax.ejb.Local;

@Local
public interface IBeanSockets{
	public void pushMessage();
	public void pushMessage(IWorkflowListener listener);
}
