package ar.com.cbarone.callsmanager.entities;

public class Operator extends Worker {

    public Operator() {
        this.setFree(true);
    }

    @Override
    public void speak() {

        System.out.println("I'm an Operator.");
    }
}
