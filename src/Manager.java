import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private final AtomicBoolean available;

    /**
     * The team of TeamLeaders.
     */
    private final HashMap<TeamLeader, Boolean> leaders = new HashMap<TeamLeader, Boolean>();

    /**
     * Default Constructor.
     */
    public Manager(Calendar time, List<TeamLeader> leaders,
            ConferenceRoom conferenceRoom, String name) {
        this.available = new AtomicBoolean(true);
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
     * Notify Manager the leader's eventStartTime.
     */
    public synchronized void notifyArrival(TeamLeader leader) {
        leaders.put(leader, true);

        try {
            this.wait();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
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
        if (this.available.compareAndSet(false, false)) {
            try {
                System.out.println(getTimeInString() + " " + name
                        + " is busy at the moment");
                System.out.flush();
                this.wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(getTimeInString() + " " + name
                + " answers a question");
        System.out.flush();
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
            System.err.println(e.getMessage());
        }
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {

        // Each event's starting time
        long eventStartTime = 0;

        if (startTime != null) {
            arrivalTime = getTime();
            arrived();
            System.out.println(getTimeInString() + " " + name
                    + " arrives at the company");
            System.out.flush();
        }

        // Do administrative stuff until all team leads arrived
        System.out.println(getTimeInString() + " " + name
                + " surfs the internet");
        System.out.flush();
        while (!hasLeadersArrived()) {
            working();
        }

        // Daily 15min meeting with team leads Notify all when back
        available.set(false);
        eventStartTime = getTime();
        System.out.println(getTimeInString() + " " + name
                + " goes to the daily 15 minutes meeting");
        System.out.flush();
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        } finally {
            System.out.println(getTimeInString() + " " + name
                    + " returns from the daily 15 minutes meeting");
            System.out.flush();
            available.set(true);
            notifyEveryone();

        }
        meetingTime += getTime() - eventStartTime;

        for (TeamLeader leader : leaders.keySet()) {
            leaders.put(leader, false);
        }

        while (hasArrived()) {
            // 10am - 11am Meeting (Finish answering first)
            if (getTime() >= 1200 && getTime() < 1800) {
                available.set(false);
                eventStartTime = getTime();
                System.out.println(getTimeInString() + " " + name
                        + " goes to the executive meeting");
                System.out.flush();
                try {
                    Thread.sleep(1800 - getTime());
                } catch (InterruptedException e) {
                    System.err.print(e.getMessage());
                } finally {
                    available.set(true);
                    notifyEveryone();
                }
                meetingTime += getTime() - eventStartTime;
                System.out.println(getTimeInString() + " " + name
                        + " returns from the executive meeting");
                System.out.flush();
            }

            // 12pm - 1pm Lunch (Finish answering first)
            if (getTime() >= 2400 && getTime() < 3000) {
                available.set(false);
                eventStartTime = getTime();
                System.out.println(getTimeInString() + " " + name
                        + " goes to lunch");
                System.out.flush();
                try {
                    Thread.sleep(3000 - getTime());
                } catch (InterruptedException e) {
                    System.err.print(e.getMessage());
                }
                lunchTime += getTime() - eventStartTime;
                System.out.println(getTimeInString() + " " + name
                        + " returns from lunch");
                System.out.flush();
                available.set(true);
                notifyEveryone();
            }

            // 2pm - 3pm Meeting (Finish answering first)
            if (getTime() >= 3600 && getTime() < 4200) {
                available.set(false);
                eventStartTime = getTime();
                System.out.println(getTimeInString() + " " + name
                        + " goes to the executive meeting");
                System.out.flush();
                try {
                    Thread.sleep(4200 - getTime());
                } catch (InterruptedException e) {
                    System.err.print(e.getMessage());
                }
                meetingTime += getTime() - eventStartTime;
                System.out.println(getTimeInString() + " " + name
                        + " returns from the executive meeting");
                System.out.flush();
                available.set(true);
                notifyEveryone();
            }

            // 4:15pm Meeting in Conference room
            if (getTime() >= 4950 && getTime() < 5100) {

                // Wait for all leaders to arrive the conference room
                while (!hasLeadersArrived()) {
                    working();
                }
                notifyEveryone();
                available.set(false);
                eventStartTime = getTime();
                System.out.println(getTimeInString() + " " + name
                        + " starts project status meeting");
                System.out.flush();
                try {
                    sleep(10);
                    conferenceRoom.lockRoom();
                } catch (InterruptedException e) {
                    System.err.print(e.getMessage());
                } finally {
                    available.set(true);
                    System.out.println(getTimeInString() + " " + name
                            + " ends project status meeting");
                    System.out.flush();
                    notifyEveryone();
                }
                meetingTime += getTime() - eventStartTime;

            }

            // 5pm Leave
            if (getTime() >= 5400) {
                System.out.println(getTimeInString() + " " + name
                        + " leaves work");
                System.out.flush();
                this.officeTime = getTime() - arrivalTime;
                left();
            }

        } // End While Loop

    } // End Run
}
