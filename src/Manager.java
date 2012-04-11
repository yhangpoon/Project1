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
     * The team of TeamLeaders.
     */
    private HashMap<TeamLeader, Boolean> leaders = new HashMap<TeamLeader, Boolean>();

    /**
     * Default Constructor.
     */
    public Manager(Calendar time, List<TeamLeader> leaders) {
        this.startTime = time;
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
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {

    }
}
