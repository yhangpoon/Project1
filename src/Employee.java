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

    protected Long lunchTime;

    protected Long meetingTime;

    protected Long waitingTime;

    protected Long workingTime;

    protected Long officeTime;

    protected Long arrivalTime;

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
     * Returns the name of the employee.
     * 
     * @return name of the employee
     */
    protected String getEmployeeName() {
        return name;
    }

    /**
     * Return the current simulated time based on the time elapsed since the
     * beginning time.
     * 
     * @return The current simulated time in a Calendar object
     */
    protected Long getTime() {
        return Calendar.getInstance().getTimeInMillis()
                - startTime.getTimeInMillis();
    }

    protected String getTimeInString() {
        String result = null;

        Long time = Calendar.getInstance().getTimeInMillis()
                - startTime.getTimeInMillis();
        Double timeInDouble = (double) time;
        timeInDouble = timeInDouble / 600 + 8;
        String timeInString = timeInDouble.toString();
        String[] timeInfo = timeInString.split("\\.");
        String hour = timeInfo[0];
        timeInDouble = Double.parseDouble("0." + timeInfo[1]);
        timeInDouble = timeInDouble * 60;
        int timeInInt = timeInDouble.intValue();
        if (timeInInt < 10) {
            result = hour + ":" + "0" + timeInInt;
        } else {
            result = hour + ":" + timeInInt;
        }
        return result;
    }

    public String getStatistics() {
        String statistics = "";
        statistics += "Time spent working: " + workingTime.toString() + "\n";
        statistics += "Time spent at lunch: " + lunchTime.toString() + "\n";
        statistics += "Time spent in meetings: " + meetingTime.toString()
                + "\n";
        statistics += "Time spent waiting: " + waitingTime.toString() + "\n";
        return statistics;
    }

    @Override
    public abstract void run();

}
