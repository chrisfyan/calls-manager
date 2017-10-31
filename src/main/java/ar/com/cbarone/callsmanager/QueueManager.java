package ar.com.cbarone.callsmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import ar.com.cbarone.callsmanager.entities.Director;
import ar.com.cbarone.callsmanager.entities.Operator;
import ar.com.cbarone.callsmanager.entities.Supervisor;
import ar.com.cbarone.callsmanager.entities.Worker;

//Queue manager tambien lo definiria como singleton
//para reducir la carga de dependencia elimino el annotation
public class QueueManager {

    private List<Worker> workers;

    public QueueManager() {
        this.workers = new ArrayList<Worker>();
    }

    public void addOperator(int qty) {

        for (int i = 0; i < qty; i++) {
            this.workers.add(new Operator());
        }
    }

    public void addSupervisor(int qty) {

        for (int i = 0; i < qty; i++) {
            this.workers.add(new Supervisor());
        }
    }

    public void addDirector(int qty) {

        for (int i = 0; i < qty; i++) {
            this.workers.add(new Director());
        }
    }

    public Worker getNextAvailable() {

        Optional<Worker> worker;

        worker = this.workers.stream().filter(w -> w.getClass() == Operator.class && w.isFree())
                .findAny();

        if (!worker.isPresent()) {
            worker = this.workers.stream()
                    .filter(w -> w.getClass() == Supervisor.class && w.isFree()).findAny();
        }

        if (!worker.isPresent()) {
            worker = this.workers.stream().filter(w -> w.getClass() == Director.class && w.isFree())
                    .findAny();
        }

        try {
            return worker.get();
        } catch (NoSuchElementException e) {
            // e.printStackTrace();
            return null;
        }
    }
    
    public List<Worker> getWorkers() {
    	return this.workers;
    }
}
