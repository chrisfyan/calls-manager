package ar.com.cbarone.callsmanager;

import java.util.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import ar.com.cbarone.callsmanager.entities.OnHoldQueue;
import ar.com.cbarone.callsmanager.entities.Worker;

/**
 * Esta clase es la encargada de disparar las llamadas recibidas
 * 
 * @author cbarone
 *
 */
//@Singleton
//En caso de un proyecto en general, definiria como singletos esta
//clase, lo comento para no depender de la dependencia de jee y
//reducir la descarga del maven
public class Dispatcher extends Observable {

	// Definicion de la maxima cantidad de llamadas que se pueden
	// manejar al mismo tiempo, si se cambia este parametro se
	//podran ejecutar mas llamadas concurrentes
	public static final int MAX_CALLS = 10;

	// El queueManager se encarga de ver quien es el siguiente disponible
	private QueueManager queueManager = new QueueManager();

	/**
	 * Se comenta esta porcion de codigo para implementar la cola de espera (no
	 * fue contemplada en el diagrama inicial) // Cola para el manejo de las
	 * llamadas en espera cuando superan las 10 // TODO: Se podria implementar
	 * una clase que posea el horario de entrada // de llamada para luego
	 * definir el tiempo que se aguardo en linea private Queue<Thread> onHold =
	 * new LinkedList<Thread>();
	 **/
	//Cola de llamadas en espera
	private OnHoldQueue onHold = new OnHoldQueue();

	// contador de llamadas activas
	private Integer callsRunning = 0;

	// Proceso de atencion de llamadas
	private Runnable runnable = () -> {
		try {
			String name = Thread.currentThread().getName();
			System.out.println("Thread: " + name);

			// Se obtiene el siguiente disponible (debería existir ya que se
			// hace el manejo por
			// cantidad de llamadas
			// aunque una alternativa sería enviar por parametro el siguiente
			// trabajador asignado)
			Worker worker = this.queueManager.getNextAvailable();
			if (worker != null) {
				worker.setFree(Boolean.FALSE);
				System.out.println("Starting call assigned to: "
						+ worker.getClass());
				worker.speak();
				Random rn = new Random();
				Integer seconds = rn.nextInt(6) + 5;
				TimeUnit.SECONDS.sleep(seconds);
				System.out.println("Call finished. Duration: " + seconds);
				worker.setFree(Boolean.TRUE);
			} else {
				System.out.println("There was an error with this call.");
			}
			this.hangUp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

	public Dispatcher(){
		//Se setea el observador, en este caso la cola de llamadas en espera
		this.addObserver(this.onHold);
	}
	
	public void dispatchCall() {

		// Si hay llamadas en espera al disparar una llamada, primero se atiende
		// y luego se dispara la nueva para que siga el proceso normal
		/**
		 * TODO: A mejorar, dado que esta es una solucion rapida: se deberia
		 * colocar un observer para que cuando haya actividad de liberacion
		 * sobre la cola de disponibles se atienda al que se encuentra en
		 * espera. Con este approach si se tiene un/os thread/s en espera y no
		 * entran llamadas, no se atenderan.
		 **/
		
		// Se comentan estas lineas de codigo para la implementacion de cola de
		// espera. Desestimar comentarios anteriores.
		
		
		// if (!this.onHold.isEmpty() && this.callsRunning <
		// Dispatcher.MAX_CALLS) {
		// this.pickUp();
		// this.onHold.poll().start();
		// }

		// Se continua el proceso normal de llamada
		Thread thread = new Thread(this.runnable);

		if (this.callsRunning < Dispatcher.MAX_CALLS) {
			this.pickUp();
			thread.start();
		} else {
			// Si el tope de llamadas se alcanzo (considerando que el tope de
			// llamadas
			// es el tope de operadores disponibles) se agrega el thread a la
			// lista de
			// espera par ser ejecutado en el orden de prioridad correspondiente
			this.onHold.add(thread);
			System.out.println("Your call was put on hold.");
		}
	}

	public void pickUp() {
		this.callsRunning++;
	}

	public void hangUp() {
		this.callsRunning--;
		setChanged();
		notifyObservers();
	}

	public boolean noCalls() {
		return this.callsRunning == 0;
	}

	public QueueManager getQueueManager() {
		return this.queueManager;
	}

	public int getCalls() {
		return this.callsRunning;
	}
}
