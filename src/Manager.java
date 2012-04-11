import java.util.Calendar;
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
        this.time = time;
        for (TeamLeader leader : leaders) {
            this.leaders.put(leader, false);
        }
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
