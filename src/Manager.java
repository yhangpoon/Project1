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
    public Manager(Calendar time, List<TeamLeader> leaders) {
        this.startTime = time;
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
    public void answerQuestion() {
        // TODO
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

    }
}
