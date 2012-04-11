import java.util.Calendar;

/**
 * This model describes a programmer, which can be a Developer and a Team
 * Leader. It implements Runnable.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
abstract class Employee extends Thread {

    /**
     * Arrival status of the programmer.
     */
    protected Boolean arrived;
    protected Calendar time;
    protected Calendar simCalendar;

    /**
     * Return the arrival status of the programmer.
     * 
     * @return arrived - the arrival status
     */
    public Boolean hasArrived() {
        return this.arrived;
    }

    /**
     * The programmer has arrived.
     */
    protected void arrived() {
        this.arrived = true;
    }

    protected Calendar getTime() {
        Calendar currentCalendar = Calendar.getInstance();
        int simulatedTime = (int) ((currentCalendar.getTimeInMillis() - time
                .getTimeInMillis()) / 10);

        simulatedTime = simulatedTime * 60 * 1000;

        simCalendar.setTimeInMillis(simulatedTime
                + simCalendar.getTimeInMillis());
        return simCalendar;
    }

    @Override
    public abstract void run();

}
