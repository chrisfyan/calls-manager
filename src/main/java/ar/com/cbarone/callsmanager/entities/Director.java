package ar.com.cbarone.callsmanager.entities;

public class Director extends Worker {

    public Director() {
        this.setFree(true);
    }

    @Override
    public void speak() {

        System.out.println("I'm a Director.");
    }
}
