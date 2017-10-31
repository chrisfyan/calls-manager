package ar.com.cbarone.callsmanager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test de 50 llamadas con
 * 3 operadores, 2 supervisores y 3 director
 * @author cbarone
 *
 */
public class CallsManager50CallsLessOperators {

	private Dispatcher dispatcher = new Dispatcher();
	
	@Test
	public void test() {
		this.dispatcher.getQueueManager().addOperator(3);
		this.dispatcher.getQueueManager().addSupervisor(2);
		this.dispatcher.getQueueManager().addDirector(3);
		
		for(int i=0; i < 50; i++){
			this.dispatcher.dispatchCall();
		}
		
		while(!dispatcher.noCalls()) {
			System.out.println("Calls still on place.");
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		assertTrue("All calls ended", dispatcher.noCalls());
	}

}
