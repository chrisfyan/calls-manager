package ar.com.cbarone.callsmanager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test de 30 llamadas con
 * 6 operador, 3 supervisores y 1 director
 * @author cbarone
 *
 */
public class CallsManager30Calls {

	private Dispatcher dispatcher = new Dispatcher();
	
	@Test
	public void test() {
		this.dispatcher.getQueueManager().addOperator(6);
		this.dispatcher.getQueueManager().addSupervisor(3);
		this.dispatcher.getQueueManager().addDirector(1);
		
		for(int i=0; i < 30; i++){
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
