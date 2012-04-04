abstract class Programmer implements Runnable {

    protected Boolean arrived;

    public Boolean hasArrived() {
        return this.arrived;
    }

    protected void arrived() {
        this.arrived = true;
    }

    @Override
    public abstract void run();

}
