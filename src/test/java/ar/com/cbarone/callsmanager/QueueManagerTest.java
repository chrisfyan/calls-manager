package ar.com.cbarone.callsmanager;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.com.cbarone.callsmanager.entities.Director;
import ar.com.cbarone.callsmanager.entities.Operator;
import ar.com.cbarone.callsmanager.entities.Supervisor;

/**
 * Test de asignacion de operadores
 * @author cbarone
 *
 */
public class QueueManagerTest {

	private Dispatcher dispatcher = new Dispatcher();
	
	@Test
	public void test() {
		this.dispatcher.getQueueManager().addOperator(6);
		this.dispatcher.getQueueManager().addSupervisor(3);
		this.dispatcher.getQueueManager().addDirector(1);
		
		long count = this.dispatcher.getQueueManager().getWorkers().stream().count();
		long countOperator = this.dispatcher.getQueueManager().getWorkers().stream().filter(w -> w.getClass() == Operator.class).count();
		long countSupervisor = this.dispatcher.getQueueManager().getWorkers().stream().filter(w -> w.getClass() == Supervisor.class).count();
		long countDirector = this.dispatcher.getQueueManager().getWorkers().stream().filter(w -> w.getClass() == Director.class).count();
		//Test 10 workers
		assertEquals(10L, count);
		//Test 6 operators
		assertEquals(6L, countOperator);
		//Test 3 Supervisors
		assertEquals(3L, countSupervisor);
		//test 1 Director
		assertEquals(1L, countDirector);
	}

}
