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

    /**
     * The time that the program started running.
     */
    protected Calendar startTime;

    /**
     * The one conference room that will be used.
     */
    protected ConferenceRoom conferenceRoom;

    /**
     * The name of the employee.
     */
    protected String name;

    /**
     * Return the arrival status of the programmer.
     * 
     * @return arrived - the arrival status
     */
    public Boolean hasArrived() {
        return this.arrived;
    }

    /**
     * The employee has arrived.
     */
    protected void arrived() {
        this.arrived = true;
    }

    /**
     * The employee has left.
     */
    protected void left() {
        this.arrived = false;
    }

    /**
     * Return the current simulated time based on the time elapsed since the
     * beginning time.
     * 
     * @return The current simulated time in a Calendar object
     */
    protected Long getTime() {
        Long time = (Long) (Calendar.getInstance().getTimeInMillis() - startTime
                .getTimeInMillis());

        time = time / 600 + 8;

        return time;
    }

    @Override
    public abstract void run();

}
