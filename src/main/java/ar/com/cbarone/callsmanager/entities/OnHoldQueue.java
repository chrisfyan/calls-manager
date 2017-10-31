package ar.com.cbarone.callsmanager.entities;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import ar.com.cbarone.callsmanager.Dispatcher;

/**
 * Esta clase observa los cambios en la liberacion de llamadas si algun operador
 * esta libre, envia la llamada para ser atendida en prioridad de llegada
 * 
 * @author cbarone
 *
 */
public class OnHoldQueue implements Observer {
	private Queue<OnHoldCall> onHold = new LinkedList<OnHoldCall>();

	@Override
	public void update(Observable o, Object arg) {
		OnHoldCall onHoldCall = this.onHold.poll();
		if (onHoldCall != null) {
			System.out.println(onHoldCall.getTimeWaited());
			((Dispatcher) o).pickUp();
			onHoldCall.getCall().run();
		}

	}

	public void add(Thread call) {
		this.onHold.add(new OnHoldCall(call));
	}

}
