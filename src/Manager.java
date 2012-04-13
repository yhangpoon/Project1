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
            ConferenceRoom conferenceRoom) {
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
    public boolean answerQuestion() {
        return true;
    }

    /**
     * Gets the availability of the Manager.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {

        if (startTime != null) {
            arrived();
        }

        while (hasArrived()) {

            // Do administrative stuff until all team leads arrived
            while (!hasLeadersArrived()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            // Daily 15min meeting with team leads Notify all when back

            // 10am - 11am Meeting (Finish answering first) Notify all when back
            // 12pm - 1pm Lunch (Finish answering first) Notify all when back
            // 2pm - 3pm Meeting (Finish answering first) Notify all when back
            // 4:15pm Meeting in Conference room
            // 5pm Leave
        }

        try {
            conferenceRoom.lockRoom();
            // TODO tell team to go to the conference room
            conferenceRoom.useRoom();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conferenceRoom.releaseRoom();
        System.out.println(Thread.currentThread().getName()
                + " is hard at work");

        // TODO randomly ask questions

        // TODO randomly decide to go to lunch

        // TODO meeting at 4:00

        // TODO leave after 8 Hours
    }
}
