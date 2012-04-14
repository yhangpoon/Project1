import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * This model describes the Project Manager.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class Manager extends Employee {

    /**
     * Availability of the Manager.
     */
    private boolean available;

    /**
     * The team of TeamLeaders.
     */
    private final HashMap<TeamLeader, Boolean> leaders = new HashMap<TeamLeader, Boolean>();

    /**
     * Default Constructor.
     */
    public Manager(Calendar time, List<TeamLeader> leaders,
            ConferenceRoom conferenceRoom, String name) {
        this.name = name;
        this.startTime = time;
        this.conferenceRoom = conferenceRoom;
        currentTime = Calendar.getInstance();
        currentTime.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, 8, 0);
        for (TeamLeader leader : leaders) {
            this.leaders.put(leader, false);
        }
    }

    /**
     * Notify Manager the leader's arrival.
     */
    public void notifyArrival(TeamLeader leader) {
        leaders.put(leader, true);
    }

    /**
     * Check to see if all leaders have arrived or not.
     */
    private Boolean hasLeadersArrived() {
        Collection<Boolean> temp = leaders.values();
        return !temp.contains(false);
    }

    /**
     * Answers Developer's question.
     */
    public synchronized boolean answerQuestion() {
        return true;
    }

    /**
     * Gets the availability of the Manager.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Notify everyone that's waiting on the Manager.
     */
    private synchronized void notifyEveryone() {
        this.notifyAll();
    }

    /**
     * Manager working.
     */
    private synchronized void working() {
        Long time = Calendar.getInstance().getTimeInMillis()
                - startTime.getTimeInMillis();
        String format = null;
        if (time / 600 + 8 >= 12) {
            format = "pm";
        } else {
            format = "am";
        }
        time = (time / 600 + 8) % 12;
        System.out.println(time + format + ": " + name
                + " is looking at porn!");
        try {
            this.wait();
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {

        // Manager Status
        boolean inMeeting = false;

        if (startTime != null) {
            arrived();
        }

        // Do administrative stuff until all team leads arrived
        while (!hasLeadersArrived()) {
            working();
        }

        // Daily 15min meeting with team leads Notify all when back
        Long timeNow = Calendar.getInstance().getTimeInMillis()
                - startTime.getTimeInMillis();
        String formatNow = null;
        if (timeNow / 600 + 8 >= 12) {
            formatNow = "pm";
        } else {
            formatNow = "am";
        }
        timeNow = (timeNow / 600 + 8) % 12;
        System.out.println(timeNow + formatNow + ": " + name
                + " goes to the daily 15 minutes meeting");
        available = false;
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            System.err.print(e.toString());
        }
        available = true;
        notifyEveryone();

        while (hasArrived()) {
            // 10am - 11am Meeting (Finish answering first)
            if (Calendar.getInstance().getTimeInMillis()
                    - startTime.getTimeInMillis() >= 1200
                    && Calendar.getInstance().getTimeInMillis()
                            - startTime.getTimeInMillis() < 1800) {
                Long time = Calendar.getInstance().getTimeInMillis()
                        - startTime.getTimeInMillis();
                String format = null;
                if (time / 600 + 8 >= 12) {
                    format = "pm";
                } else {
                    format = "am";
                }
                time = (time / 600 + 8) % 12;
                System.out.println(time + format + ": " + name
                        + " goes to the executive meeting");
                available = false;
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                available = true;
                notifyEveryone();
            }

            // 12pm - 1pm Lunch (Finish answering first)
            if (Calendar.getInstance().getTimeInMillis()
                    - startTime.getTimeInMillis() >= 2400
                    && Calendar.getInstance().getTimeInMillis()
                            - startTime.getTimeInMillis() < 3000) {
                Long time = Calendar.getInstance().getTimeInMillis()
                        - startTime.getTimeInMillis();
                String format = null;
                if (time / 600 + 8 >= 12) {
                    format = "pm";
                } else {
                    format = "am";
                }
                time = (time / 600 + 8) % 12;
                System.out.println(time + format + ": " + name
                        + " goes to lunch");
                available = false;
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                available = true;
                notifyEveryone();
            }

            // 2pm - 3pm Meeting (Finish answering first)
            if (Calendar.getInstance().getTimeInMillis()
                    - startTime.getTimeInMillis() >= 3600
                    && Calendar.getInstance().getTimeInMillis()
                            - startTime.getTimeInMillis() < 4200) {
                Long time = Calendar.getInstance().getTimeInMillis()
                        - startTime.getTimeInMillis();
                String format = null;
                if (time / 600 + 8 >= 12) {
                    format = "pm";
                } else {
                    format = "am";
                }
                time = (time / 600 + 8) % 12;
                System.out.println(time + format + ": " + name
                        + " goes to the executive meeting");
                available = false;
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                available = true;
                notifyEveryone();
            }

            // 4:15pm Meeting in Conference room
            if (Calendar.getInstance().getTimeInMillis()
                    - startTime.getTimeInMillis() >= 4950
                    && inMeeting == false) {
                Long time = Calendar.getInstance().getTimeInMillis()
                        - startTime.getTimeInMillis();
                String format = null;
                if (time / 600 + 8 >= 12) {
                    format = "pm";
                } else {
                    format = "am";
                }
                time = (time / 600 + 8) % 12;
                System.out.println(time + format + ": " + name
                        + " goes to the project status meeting");
                available = false;
                inMeeting = true;
                try {
                    conferenceRoom.lockRoom();
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                available = true;
            }

            // 5pm Leave
            if (Calendar.getInstance().getTimeInMillis()
                    - startTime.getTimeInMillis() >= 5400) {
                Long time = Calendar.getInstance().getTimeInMillis()
                        - startTime.getTimeInMillis();
                String format = null;
                if (time / 600 + 8 >= 12) {
                    format = "pm";
                } else {
                    format = "am";
                }
                time = (time / 600 + 8) % 12;
                System.out.println(time + format + ": " + name
                        + " leaves work");
                left();
            }
        }
    }

    @Override
    public void interrupt() {
        if (!isAvailable()) {
            return;
        }
        while (!isAvailable()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.print(e.toString());
            }
        }
    }
}
