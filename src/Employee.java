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
     * For Statistic Purpose.
     */
    protected long lunchTime, meetingTime, waitingTime, workingTime,
            officeTime, arrivalTime;

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
     * @return The current simulated time in Millisecond
     */
    protected Long getTime() {
        return Calendar.getInstance().getTimeInMillis()
                - startTime.getTimeInMillis();
    }

    /**
     * Return the current simulated time in hour in String.
     * 
     * @return The current simulated time in String
     */
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

    /**
     * Return a String of all the statistics about this employee.
     * 
     * @return statistics of the employee
     */
    public String getStatistics() {
        String result = "";
        workingTime = officeTime - lunchTime - meetingTime - waitingTime;
        result += "Employee: " + name + "\n";
        result += "Total Time in Office: " + officeTime / 10 + " mins \n";
        result += "Time spent working: " + workingTime / 10 + " mins \n";
        result += "Time spent at lunch: " + lunchTime / 10 + " mins \n";
        result += "Time spent in meetings: " + meetingTime / 10 + " mins \n";
        result += "Time spent waiting: " + waitingTime / 10 + " mins \n";
        return result;
    }

    /**
     * All employees must override this method.
     */
    @Override
    public abstract void run();

}
