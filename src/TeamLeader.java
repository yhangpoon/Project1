import java.util.Calendar;

/**
 * This model describes a Team Leader. It extends Programmer.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class TeamLeader extends Employee {

    /**
     * The time
     */
    private Calendar time;

    /**
     * The Team Leader's project manager.
     */
    private final Manager manager;

    /**
     * Default Constructor.
     * 
     * @param manager
     *            - The assigned project manager
     */
    public TeamLeader(Manager manager, Calendar time) {
        this.manager = manager;
        this.time = time;
        this.arrived = false;
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
        // TODO Auto-generated method stub

    }

}
