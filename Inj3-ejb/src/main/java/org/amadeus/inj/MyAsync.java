package org.amadeus.inj;
import javax.ejb.Local;

@Local
public interface MyAsync {
	public void pushMessage();

}
