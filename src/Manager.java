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
        for (TeamLeader leader : leaders) {
            this.leaders.put(leader, false);
        }
    }

    /**
     * Notify Manager the leader's arrival.
     */
    public synchronized void notifyArrival(TeamLeader leader) {
        leaders.put(leader, true);
        try {
            this.wait();
        } catch (InterruptedException e) {
            System.err.println(e.toString());
        }
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
        if (available == false) {
            try {
                System.out.println(getTimeInString() + " " + name
                        + " is busy at the moment");
                this.wait();
            } catch (InterruptedException e) {
                System.err.println(e.toString());
            }
        }
        System.out.println(getTimeInString() + " " + name
                + " answers a question");
        return true;
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
        System.out.println(getTimeInString() + " " + name
                + " is looking at porn!");
        try {
            this.wait();
        } catch (InterruptedException e) {
            System.err.println(e.toString());
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
        available = false;
        notifyEveryone();
        System.out.println(getTimeInString() + " " + name
                + " goes to the daily 15 minutes meeting");
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            System.err.print(e.toString());
        }
        available = true;
        notifyEveryone();

        while (hasArrived()) {
            // 10am - 11am Meeting (Finish answering first)
            if (getTime() >= 10 && getTime() < 11) {
                System.out.println(getTimeInString() + " " + name
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
            if (getTime() >= 12 && getTime() < 13) {
                System.out.println(getTimeInString() + " " + name
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
            if (getTime() >= 14 && getTime() < 15) {
                System.out.println(getTimeInString() + " " + name
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
            if (getTime() >= 16.25 && inMeeting == false) {
                System.out.println(getTimeInString() + " " + name
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
            if (getTime() >= 17) {
                System.out.println(getTimeInString() + " " + name
                        + " leaves work");
                left();
            }
        }
    }
}
