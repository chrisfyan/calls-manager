package ar.com.cbarone.callsmanager.entities;

public class Supervisor extends Worker {

    public Supervisor() {
        this.setFree(true);
    }

    @Override
    public void speak() {

        System.out.println("I'm a Supervisor.");
    }

}
