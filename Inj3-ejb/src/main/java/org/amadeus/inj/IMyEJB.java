package org.amadeus.inj;

import javax.ejb.Local;

@Local		
public interface IMyEJB {
	public void start(IWorkflowListener listener);
}
