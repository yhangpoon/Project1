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
        this.startTime = time;
        this.lunchTime = 0;
        this.meetingTime = 0;
        this.workingTime = 0;
        this.officeTime = 0;
        this.name = name;
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
        try {
            this.wait(10);
        } catch (InterruptedException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {

        long arrival = 0;

        // Manager Status
        boolean inMeeting = false;

        if (startTime != null) {
            arrivalTime = getTime();
            arrived();
            System.out.println(getTimeInString() + " " + name
                    + " arrives at the company");
        }

        // Do administrative stuff until all team leads arrived
        System.out.println(getTimeInString() + " " + name
                + " surfs the internet");
        while (!hasLeadersArrived()) {
            working();
        }

        // Daily 15min meeting with team leads Notify all when back
        available = false;
        System.out.println(getTimeInString() + " " + name
                + " goes to the daily 15 minutes meeting");
        try {
            arrival = getTime();
            Thread.sleep(150);
            meetingTime += getTime() - arrival;
        } catch (InterruptedException e) {
            System.err.print(e.toString());
        }
        available = true;
        notifyEveryone();

        while (hasArrived()) {
            // 10am - 11am Meeting (Finish answering first)
            if (getTime() >= 1200 && getTime() < 1800) {
                System.out.println(getTimeInString() + " " + name
                        + " goes to the executive meeting");
                available = false;
                try {
                    arrival = getTime();
                    Thread.sleep(600);
                    meetingTime += getTime() - arrival;
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                available = true;
                notifyEveryone();
            }

            // 12pm - 1pm Lunch (Finish answering first)
            if (getTime() >= 2400 && getTime() < 3000) {
                arrival = getTime();
                System.out.println(getTimeInString() + " " + name
                        + " goes to lunch");
                available = false;
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                lunchTime += getTime() - arrival;
                available = true;
                notifyEveryone();
            }

            // 2pm - 3pm Meeting (Finish answering first)
            if (getTime() >= 3600 && getTime() < 4200) {
                arrival = getTime();
                System.out.println(getTimeInString() + " " + name
                        + " goes to the executive meeting");
                available = false;
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                meetingTime += getTime() - arrival;
                available = true;
                notifyEveryone();
            }

            // 4:15pm Meeting in Conference room
            if (getTime() >= 4950 && inMeeting == false) {
                arrival = getTime();
                System.out.println(getTimeInString() + " " + name
                        + " goes to the project status meeting");
                available = false;
                inMeeting = true;
                try {
                    conferenceRoom.lockRoom();
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                meetingTime += getTime() - arrival;
                available = true;
            }

            // 5pm Leave
            if (getTime() >= 5400) {
                System.out.println(getTimeInString() + " " + name
                        + " leaves work");
                this.officeTime = getTime() - arrivalTime;
                left();
            }
        }
    }
}
