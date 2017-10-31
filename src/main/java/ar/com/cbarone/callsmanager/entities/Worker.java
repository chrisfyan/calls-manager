package ar.com.cbarone.callsmanager.entities;

/**
 * Esta clase funciona para darle polimofismo a los tipos de empleados consta del atributo free, el
 * cual indicara si se encuentra libre
 * 
 * @author cbarone
 *
 */
public abstract class Worker {

    private boolean free;

    /**
     * Devuelve el valor de free.
     *
     * @return El valor de free.
     */
    public boolean isFree() {

        return free;
    }

    /**
     * Asigna un nuevo valor a free.
     *
     * @param free El valor a asignar a free.
     */
    public void setFree(boolean free) {

        this.free = free;
    }

    public void pickUpPhone() {

        this.free = false;
    }

    public void hangUpPhone() {

        this.free = true;
    }

    public abstract void speak();

}
